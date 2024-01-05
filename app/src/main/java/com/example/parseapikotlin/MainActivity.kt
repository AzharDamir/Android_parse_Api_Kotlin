package com.example.parseapikotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.parseapikotlin.adapter.CountryListAdapter
import com.example.parseapikotlin.viewModel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    lateinit var recyclerAdapter: CountryListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
        initViewModel()
    }

    private fun initRecyclerView() {
        val countryListRecyclerview: RecyclerView = findViewById(R.id.countryListRecyclerview)
        countryListRecyclerview.layoutManager = LinearLayoutManager(this)
        recyclerAdapter = CountryListAdapter(this)
        countryListRecyclerview.adapter = recyclerAdapter
    }

    /*private fun initViewModel() {
        val viewModel:  MainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getLiveDataObserver().observe(this, Observer {
            if(it != null) {
                recyclerAdapter.setCountryList(it)
                recyclerAdapter.notifyDataSetChanged()
            } else {
                //Log.e("MainViewModel", "API request failed  ${it}")
                Toast.makeText(this, "Error in getting list", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.makeAPICall()
    }*/
    private fun initViewModel() {
        val viewModel: MainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getLiveDataObserver().observe(this, Observer { countryList ->
            if (countryList != null && countryList.isNotEmpty()) {
                recyclerAdapter.setCountryList(countryList)
                recyclerAdapter.notifyDataSetChanged()
            } else {
                // Handle the scenario where countryList is null or empty
                Toast.makeText(this, "Error in getting list", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.makeAPICall()
    }

}