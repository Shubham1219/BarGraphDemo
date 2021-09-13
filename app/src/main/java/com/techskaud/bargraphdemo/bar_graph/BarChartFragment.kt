package com.techskaud.bargraphdemo.bar_graph

import android.content.Intent
import android.graphics.RectF
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.example.woohoo.base.BaseFragment
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.Legend.LegendForm
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.XAxis.XAxisPosition
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.components.YAxis.AxisDependency
import com.github.mikephil.charting.components.YAxis.YAxisLabelPosition
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.MPPointF
import com.techskaud.bargraphdemo.R
import com.techskaud.bargraphdemo.activity.StepCountActivty
import kotlinx.android.synthetic.main.bar_chat_layout.*
import java.util.ArrayList

class BarChartFragment : BaseFragment(), OnChartValueSelectedListener {
    override fun getLayoutID(): Int {
        return R.layout.bar_chat_layout
    }

    override fun onCreateView() {
        setData()
        setBarGraph()
        Handler(Looper.getMainLooper()).postDelayed({
                                                    val intent = Intent(requireActivity(),StepCountActivty::class.java)
            startActivity(intent)

        },2500)
    }
    fun setBarGraph(){
        chart.setOnChartValueSelectedListener(this)

        chart.setDrawBarShadow(false)
        chart.setDrawValueAboveBar(true)

        chart.getDescription().setEnabled(false)

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        chart.setMaxVisibleValueCount(60)

        // scaling can now only be done on x- and y-axis separately

        // scaling can now only be done on x- and y-axis separately
        chart.setPinchZoom(false)

        chart.setDrawGridBackground(false)
        // chart.setDrawYLabels(false);

        // chart.setDrawYLabels(false);
        val xAxisFormatter: ValueFormatter = DayAxisValueFormatter(chart)

        val xAxis: XAxis = chart.getXAxis()
        xAxis.position = XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)
        xAxis.granularity = 1f // only intervals of 1 day

        xAxis.labelCount = 7
        xAxis.setValueFormatter(xAxisFormatter)

        val custom: ValueFormatter = MyAxisValueFormatter()

        val leftAxis: YAxis = chart.getAxisLeft()
        leftAxis.setLabelCount(8, false)
        leftAxis.setValueFormatter(custom)
        leftAxis.setPosition(YAxisLabelPosition.OUTSIDE_CHART)
        leftAxis.spaceTop = 15f
        leftAxis.axisMinimum = 0f // requireActivity() replaces setStartAtZero(true)


        val rightAxis: YAxis = chart.getAxisRight()
        rightAxis.setDrawGridLines(false)

        rightAxis.setLabelCount(8, false)
        rightAxis.setValueFormatter(custom)
        rightAxis.spaceTop = 15f
        rightAxis.axisMinimum = 0f // requireActivity() replaces setStartAtZero(true)

        val l: Legend = chart.getLegend()
        l.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
        l.orientation = Legend.LegendOrientation.HORIZONTAL
        l.setDrawInside(false)
        l.form = LegendForm.SQUARE
        l.formSize = 9f
        l.textSize = 11f
        l.xEntrySpace = 4f



    }
    private val onValueSelectedRectF = RectF()

    override fun onValueSelected(e: Entry?, h: Highlight?) {
        if (e == null) return

        val bounds: RectF = onValueSelectedRectF
        chart.getBarBounds(e as BarEntry?, bounds)
        val position = chart.getPosition(e, AxisDependency.LEFT)

        Log.i("bounds", bounds.toString())
        Log.i("position", position.toString())

        Log.i(
            "x-index",
            "low: " + chart.lowestVisibleX + ", high: "
                    + chart.highestVisibleX
        )

        MPPointF.recycleInstance(position)
    }

    override fun onNothingSelected() {

    }

    private fun setData() {
        val values = ArrayList<BarEntry>()

        for (i in 0 until 18) {
            val multi: Float = (18 + 1).toFloat()
            val `val` = (Math.random() * multi).toFloat() + multi / 3
            values.add(BarEntry(i.toFloat(), `val`))
        }

        val set1: BarDataSet

        if (chart.data != null &&
            chart.data.dataSetCount > 0
        ) {
            set1 = chart.data.getDataSetByIndex(0) as BarDataSet
            set1.values = values
            chart.data.notifyDataChanged()
            chart.notifyDataSetChanged()
        } else {
            set1 = BarDataSet(values, "Data Set")
            set1.setColors(*ColorTemplate.VORDIPLOM_COLORS)
            set1.setDrawValues(false)
            val dataSets = ArrayList<IBarDataSet>()
            dataSets.add(set1)
            val data = BarData(dataSets)
            chart.data = data
            chart.setFitBars(true)
        }

        chart.invalidate()

    }
}


