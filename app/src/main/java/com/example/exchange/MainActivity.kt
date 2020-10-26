package com.example.exchange

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exchange.databinding.ActivityMainBinding
import kotlin.properties.Delegates


class MainActivity : AppCompatActivity(), SharedPreferences.OnSharedPreferenceChangeListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPref: SharedPreferences

    private val viewModel: HostViewModel by lazy {
        ViewModelProvider(this).get(HostViewModel::class.java)
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: com.example.exchange.ListAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    private var period by Delegates.notNull<Int>()
    private var currency by Delegates.notNull<String>()
    private var crypt by Delegates.notNull<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setValuesFromSharedPref()

        setUrlEventListener()

        val dataSet: List<ExampleData> = getDataForRecyclerView();
        setRecyclerViewData(dataSet);

        val popupMenu = PopupMenu(this, binding.buttonId)
        popupMenu.inflate(R.menu.popupmenu)
        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menu1 -> {
                    binding.buttonId.text = "Bitcoin"
                    true
                }
                R.id.menu2 -> {
                    binding.buttonId.text = "Ethereum"
                    true
                }
                R.id.menu3 -> {
                    binding.buttonId.text = "Litecoin"
                    true
                }
                R.id.menu4 -> {
                    binding.buttonId.text = "Chainlink"
                    true
                }
                R.id.menu5 -> {
                    binding.buttonId.text = "Bitcoin Cash"
                    true
                }

                else -> false
            }
        }

        binding.buttonId.setOnClickListener {
            popupMenu.show()
        }

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

    private fun getDataForRecyclerView(): List<ExampleData> {
        val dataSet: List<ExampleData> = listOf(
            ExampleData(viewModel.response.toString(), "hello", viewModel.response.toString()),
            ExampleData(viewModel.response.toString(), "hello", viewModel.response.toString()),
            ExampleData(viewModel.response.toString(), "hello", viewModel.response.toString()),
            ExampleData(viewModel.response.toString(), "hello", viewModel.response.toString()),
            ExampleData(viewModel.response.toString(), "hello", viewModel.response.toString()),
            ExampleData(viewModel.response.toString(), "hello", viewModel.response.toString()),
            ExampleData(viewModel.response.toString(), "hello", viewModel.response.toString()),
            ExampleData(viewModel.response.toString(), "hello", viewModel.response.toString())
        )
        return dataSet
    }

    private fun setRecyclerViewData(dataSet: List<ExampleData>) {
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
//            item,
//            Toast.LENGTH_LONG
//        ).show()
        Toast.makeText(
            this,
            //TODO: вместо item максимальное и минимальное значение этой валюты за день
            item.substring(0, 3) + " for " + item.substring(3, 4) + "\n" + //поправить конечный индекс на длину даты
                    "MAX" + "\n" +
                    "MIN",
            Toast.LENGTH_LONG
        ).show()
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
//            resources.getString(R.string.preference_file_key_crypto) -> {
//                val defaultCryptoValue =
//                    resources.getString(R.string.preference_file_key_crypto_default)
//                crypt = sharedPref.getString(key, defaultCryptoValue).toString()
//                Log.v("KEK CRYPTO", crypt)
//            }
            else -> {
                Log.v("KEK WHAT", "Error")
            }
        }
    }
}
