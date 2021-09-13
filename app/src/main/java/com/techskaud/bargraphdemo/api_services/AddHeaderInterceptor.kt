package com.example.woohoo.retrofit

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.util.*

class AddHeaderInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        val builder = chain.request().newBuilder()
        //future use
//        val token = "Bearer " + SharedPreferences.prefs?.getValue(Constant.ACCESS_TOKEN, "")
//        builder.addHeader("timezone",getTimeZone())
//        builder.addHeader("Authorization", token)

        getTimeZone()
        return chain.proceed(builder.build())

    }

    fun getTimeZone():String{
        val tz = TimeZone.getDefault()
        val offset = tz.rawOffset
        val timeZone = String.format("%s%02d:%02d", if (offset >= 0) "+" else "-", offset / 3600000, offset / 60000 % 60)
        Log.e("newTimezone",timeZone)
        return timeZone
    }

}