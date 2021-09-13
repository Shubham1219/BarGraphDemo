package com.techskaud.bargraphdemo.fragments

import androidx.navigation.fragment.findNavController
import com.example.woohoo.base.BaseFragment
import com.techskaud.bargraphdemo.R

class SplashFragment : BaseFragment() {
    override fun getLayoutID(): Int {
        return R.layout.splash_frgament
    }

    override fun onCreateView() {
       findNavController().navigate(R.id.action_splashFragment_to_barChartFragment)
    }
}