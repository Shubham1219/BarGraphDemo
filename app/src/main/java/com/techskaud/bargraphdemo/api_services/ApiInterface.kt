package com.techskaud.sampleapp.api_services


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


interface ApiInterface {


    companion object {
        public fun getApiService() = Retrofit.Builder()
            .baseUrl("https://reqres.in/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
    }

}