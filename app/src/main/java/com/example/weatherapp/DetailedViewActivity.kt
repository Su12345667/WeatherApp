package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Process
import android.widget.Button
import android.widget.TextView
import kotlin.system.exitProcess

class DetailedViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_view)

        val exitBtn = findViewById<Button>(R.id.button2)
        val backBtn = findViewById<Button>(R.id.backBtn)
        val averageTextView = findViewById<TextView>(R.id.average)
        val detailedView = findViewById<TextView>(R.id.detailedView)
        val minData = mutableListOf<Int>()
        val maxData = mutableListOf<Int>()

        intent.getIntegerArrayListExtra("minData")?.let{ minData.addAll(it)}
        intent.getIntegerArrayListExtra("maxData")?.let{ maxData.addAll(it)}

        fun displayDetailedView(){
            val detailedViewText = StringBuilder()
            for ((index, screenTime) in minData.withIndex()){
                detailedViewText.append("Day ${index + 1}: $screenTime minutes\n")
            }
            detailedView.text = detailedViewText.toString()
        }

        fun calculateMinAverage() {
            val totalMin =minData.sum()
            val averageMinData = if(minData.isNotEmpty()){
                totalMin / minData.size
            } else{
                0
            }
            averageTextView.text = "Average Min Temp: $averageMinData degrees"
        }
        fun calculateMaxAverage() {
            val totalMax =maxData.sum()
            val averageMaxData = if(maxData.isNotEmpty()){
                totalMax / maxData.size
            } else{
                0
            }
            averageTextView.text = "Average Max Temp: $averageMaxData degrees"
        }

        displayDetailedView()
        calculateMinAverage()
        calculateMaxAverage()

        backBtn.setOnClickListener {
            finish()
        }
        exitBtn.setOnClickListener {
            Process.killProcess(Process.myPid())
            exitProcess(1)
        }
    }
}