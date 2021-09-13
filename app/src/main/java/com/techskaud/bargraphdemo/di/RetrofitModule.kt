package com.techskaud.sampleapp.di

import com.example.woohoo.retrofit.AddHeaderInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.techskaud.sampleapp.api_services.ApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .setLenient()
            .create()
    }



    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit.Builder {
            //user for header future use
//        val httpClient = OkHttpClient.Builder()
//        httpClient.addInterceptor { chain ->
//            val request = chain.request()
//                .newBuilder()
//                .addHeader("Accept","application/json")
//                .addHeader("Authorization", token)
//                .build()
//            chain.proceed(request)
//        }
        val mRetrofit = Retrofit.Builder()
            .baseUrl("")//base url
            .addConverterFactory(GsonConverterFactory.create())
            .client(getClient())
            .addConverterFactory(GsonConverterFactory.create(gson))
        return  mRetrofit

    }

    @Singleton
    @Provides
    fun provideBlogService(retrofit: Retrofit.Builder): ApiInterface {
        return retrofit
            .build()
            .create(ApiInterface::class.java)
    }

    // Creating OkHttpclient Object
    private fun getClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient().newBuilder().connectTimeout(2, TimeUnit.MINUTES)
            .readTimeout(2, TimeUnit.MINUTES)
            .writeTimeout(2, TimeUnit.MINUTES)
            .addInterceptor(interceptor)
            .addNetworkInterceptor(AddHeaderInterceptor())
            .build()

    }
}