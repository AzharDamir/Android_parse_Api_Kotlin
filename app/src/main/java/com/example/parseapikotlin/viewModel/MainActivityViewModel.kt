package com.example.parseapikotlin.viewModel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.parseapikotlin.data.CountryModel
import com.example.parseapikotlin.retrofit.RetroInstance
import com.example.parseapikotlin.retrofit.RetroServiceInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel: ViewModel() {

    var liveDataList: MutableLiveData<List<CountryModel>>

    init {
        liveDataList = MutableLiveData()
    }


    fun getLiveDataObserver(): MutableLiveData<List<CountryModel>> {
        return liveDataList
    }

    fun makeAPICall() {
        val retroInstance = RetroInstance.getRetroInstance()
        val retroService  = retroInstance.create(RetroServiceInterface::class.java)
        val call  = retroService.getCountryList()
        call.enqueue(object : Callback<List<CountryModel>> {
            override fun onFailure(call: Call<List<CountryModel>>, t: Throwable) {
                System.out.println("failure sad: ${t.message}")
                liveDataList.postValue(null)
            }
            override fun onResponse(
                call: Call<List<CountryModel>>,
                response: Response<List<CountryModel>>
            ) {
                System.out.println("we are on response")
                if (response.isSuccessful) {
                    val countryList = response.body()
                    if (countryList != null) {
                        System.out.println("response: $countryList")
                        liveDataList.postValue(countryList)
                    } else {
                        System.out.println("Response body is empty!")
                        liveDataList.postValue(null) // Consider your approach if response.body() is null
                    }
                } else {
                    System.out.println("Response unsuccessful: ${response.code()}")
                    liveDataList.postValue(null)
                }
            }
           /* override fun onResponse(
                call: Call<List<CountryModel>>,
                response: Response<List<CountryModel>>
            ) {
                System.out.println("response"+response.body())
                liveDataList.postValue(response.body())
            }*/
        })


    }
}