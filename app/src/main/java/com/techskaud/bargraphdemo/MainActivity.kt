package com.techskaud.bargraphdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.woohoo.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    override fun getLayoutId(): Int {
        return  R.layout.activity_main
    }

    override fun onLayoutCreated() {

    }


}