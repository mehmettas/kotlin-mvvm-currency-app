package com.mehmettas.cent.utils.extensions

import android.graphics.Color
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.android.synthetic.main.activity_currency_detail.*

fun drawChart(dataChart:LineChart,valuesOfRates:ArrayList<String>)
{
    var chartValues:ArrayList<Entry> = arrayListOf()

    for(x in 0 until valuesOfRates.size)
    {
        chartValues.add( Entry(x.toFloat(), "%.1f".format(valuesOfRates[x].toDouble()).toFloat()));
    }

    val lineDataSet = LineDataSet(chartValues, "")
    lineDataSet.axisDependency = YAxis.AxisDependency.LEFT
    lineDataSet.isHighlightEnabled = false
    lineDataSet.lineWidth = 1F
    lineDataSet.color = Color.WHITE
    lineDataSet.setCircleColor(Color.WHITE)
    lineDataSet.circleRadius = 1.5F
    lineDataSet.circleHoleRadius = .5F
    lineDataSet.setDrawHighlightIndicators(true)
    lineDataSet.valueTextSize = 0F
    lineDataSet.valueTextColor = Color.WHITE

    val lineData = LineData(lineDataSet)

    dataChart.description.textSize =12F
    dataChart.setDrawMarkers(true)
    dataChart.xAxis.position = XAxis.XAxisPosition.BOTH_SIDED
    dataChart.animateXY(500, 500)
    dataChart.xAxis.isGranularityEnabled = true
    dataChart.xAxis.granularity = 1.0F
    dataChart.description.isEnabled = false
    dataChart.axisLeft.isEnabled = false
    dataChart.axisRight.axisLineWidth = 0F
    dataChart.axisRight.axisLineColor = Color.WHITE
    dataChart.axisRight.setDrawAxisLine(false)
    dataChart.axisRight.textColor = Color.WHITE
    dataChart.axisRight.setDrawGridLines(false)
    dataChart.axisLeft.setDrawGridLines(false)
    dataChart.xAxis.setDrawGridLines(false)
    dataChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
    dataChart.xAxis.axisLineColor = Color.WHITE
    dataChart.xAxis.isEnabled = false
    dataChart.legend.isEnabled = false
    dataChart.data = lineData

}