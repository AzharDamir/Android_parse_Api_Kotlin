package com.example.parseapikotlin.retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
class RetroInstance {
    companion object {
        val BASE_URL = "https://restcountries.com/v3.1/"//v2/

        fun getRetroInstance(): Retrofit {
            /*return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()*/
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // Add this line for logging
                .client(OkHttpClient.Builder().addInterceptor(interceptor).build()) // Attach the interceptor
                .build()
        }
    }
}