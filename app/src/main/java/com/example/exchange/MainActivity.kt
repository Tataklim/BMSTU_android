package com.example.exchange

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exchange.databinding.ActivityMainBinding
import org.json.JSONObject
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: HostViewModel by lazy {
        ViewModelProvider(this).get(HostViewModel::class.java)
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: com.example.exchange.ListAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
        val item_id = item.itemId
        if (item_id == R.id.action_settings) {
            //
            return true
        }

        if (item_id == R.id.update_settings) {
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