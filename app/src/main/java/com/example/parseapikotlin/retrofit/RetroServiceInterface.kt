package com.example.parseapikotlin.retrofit

import com.example.parseapikotlin.data.CountryModel
import retrofit2.Call
import retrofit2.http.GET

interface RetroServiceInterface {
    @GET("all")
    fun getCountryList(): Call<List<CountryModel>>
}