package com.example.exchange

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exchange.databinding.ActivityMainBinding
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import kotlin.properties.Delegates


class MainActivity : AppCompatActivity(), SharedPreferences.OnSharedPreferenceChangeListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPref: SharedPreferences

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: com.example.exchange.ListAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    private var period by Delegates.notNull<Int>()
    private var currency by Delegates.notNull<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setValuesFromSharedPref()

        setUrlEventListener()

        getAndSetDataForRecyclerView();

//        val myDataSet: List<ExampleData> = ExampleApi.retrofitService.getData()
    }

    override fun onDestroy() {
        super.onDestroy()
        PreferenceManager.getDefaultSharedPreferences(this)
            .unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val itemId = item.itemId
        if (itemId == R.id.action_settings) {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
            return true
        }

        if (itemId == R.id.update_settings) {
            recreate()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setValuesFromSharedPref() {
        sharedPref = this.getSharedPreferences(
            getString(R.string.preference_file_key), Context.MODE_PRIVATE
        )
        sharedPref.registerOnSharedPreferenceChangeListener(this)

        val defaultPeriodValue = resources.getInteger(R.integer.preference_file_key_period_default)
        val defaultCurrencyValue =
            resources.getString(R.string.preference_file_key_currency_default)
        period =
            sharedPref.getInt(getString(R.string.preference_file_key_period), defaultPeriodValue)
        currency = sharedPref.getString(
            getString(R.string.preference_file_key_currency),
            defaultCurrencyValue
        ).toString()
    }

    private fun setUrlEventListener() {
        val urlTextView = findViewById<TextView>(R.id.url)
        urlTextView.setOnClickListener {
            val parsedUri: Uri = Uri.parse(urlTextView.text.toString())
            intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(parsedUri.toString())
            startActivity(intent)
        }
    }

    private fun getAndSetDataForRecyclerView(): MutableList<DayInfo> {
        val dataSet: MutableList<DayInfo> = mutableListOf()

        val apiService = ApiService.create()
        apiService.search("BTC", "USD", "10")
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe ({
                    result ->
                result.Data.List.map { elem -> dataSet.add(elem) }
                setRecyclerViewData(dataSet);
            }, { error ->
                error.printStackTrace()
            })
        return dataSet
    }

    private fun setRecyclerViewData(dataSet: MutableList<DayInfo>) {
        viewManager = LinearLayoutManager(this)
        viewAdapter = ListAdapter { item -> itemClicked(item) }
        viewAdapter.data = dataSet

        recyclerView = findViewById<RecyclerView>(R.id.recyclerViewId).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    private fun itemClicked(item: String) {
//        Toast.makeText(
//            this,
//            "If you want to take this cutie" + "\n" + "Call +7 (916) 509-38-42",
//            Toast.LENGTH_LONG
//        ).show()
        Log.v("KEK", "itemClicked")
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String) {
        when (key) {
            resources.getString(R.string.preference_file_key_period) -> {
                val defaultPeriodValue =
                    resources.getInteger(R.integer.preference_file_key_period_default)
                period = sharedPref.getInt(key, defaultPeriodValue)
                Log.v("KEK PERIOD", period.toString())
            }
            resources.getString(R.string.preference_file_key_currency) -> {
                val defaultCurrencyValue =
                    resources.getString(R.string.preference_file_key_currency_default)
                currency = sharedPref.getString(key, defaultCurrencyValue).toString()
                Log.v("KEK CURRENCY", currency)
            }
            else -> {
                Log.v("KEK WHAT", "Error")
            }
        }
    }
}
