package com.techskaud.bargraphdemo.bar_graph

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import java.text.DecimalFormat

class MyAxisValueFormatter () : ValueFormatter() {

    private var mFormat: DecimalFormat? = null

    override fun getFormattedValue(value: Float, axis: AxisBase?): String {
        mFormat = DecimalFormat("###,###,###,##0.0")
        return mFormat!!.format(value.toDouble()) + " $"
    }
}