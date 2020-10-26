package com.example.exchange

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exchange.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPref: SharedPreferences

    private val viewModel: HostViewModel by lazy {
        ViewModelProvider(this).get(HostViewModel::class.java)
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: com.example.exchange.ListAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        sharedPref = this.getSharedPreferences(
            getString(R.string.preference_file_key), Context.MODE_PRIVATE)

        val defaultPeriodValue = resources.getInteger(R.integer.preference_file_key_period_default)
        val defaultCurrencyValue = resources.getString(R.string.preference_file_key_currency_default)
        val period = sharedPref.getInt(getString(R.string.preference_file_key_period), defaultPeriodValue)
        val currency = sharedPref.getString(getString(R.string.preference_file_key_period), defaultCurrencyValue)

        // n days!!!

//        val myDataSet: List<ExampleData> = ExampleApi.retrofitService.getData()

        val myDataSet: List<ExampleData> = listOf(ExampleData(viewModel.response.toString(), "hello", viewModel.response.toString()),
            ExampleData(viewModel.response.toString(), "hello", viewModel.response.toString()),
            ExampleData(viewModel.response.toString(), "hello", viewModel.response.toString()),
            ExampleData(viewModel.response.toString(), "hello", viewModel.response.toString()),
            ExampleData(viewModel.response.toString(), "hello", viewModel.response.toString()),
            ExampleData(viewModel.response.toString(), "hello", viewModel.response.toString()),
            ExampleData(viewModel.response.toString(), "hello", viewModel.response.toString()),
            ExampleData(viewModel.response.toString(), "hello", viewModel.response.toString()))


        viewManager = LinearLayoutManager(this)
        viewAdapter = ListAdapter { item -> itemClicked(item) }
        viewAdapter.data = myDataSet

        recyclerView = findViewById<RecyclerView>(R.id.recyclerViewId).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
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

    private fun itemClicked(item: String) {
        Toast.makeText(
            this,
            "If you want to take this cutie" + "\n" + "Call +7 (916) 509-38-42",
            Toast.LENGTH_LONG
        ).show()
    }

}