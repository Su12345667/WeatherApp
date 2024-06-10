package com.example.weatherapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val minData = mutableListOf<Int>()
        val maxData = mutableListOf<Int>()
        val minEditText = findViewById<EditText>(R.id.editText)
        val maxEditText = findViewById<EditText>(R.id.editText2)
        val addButton = findViewById<Button>(R.id.addBtn)
        val clearButton = findViewById<Button>(R.id.clearBtn)
        val btnView = findViewById<Button>(R.id.btnView)
        val maxAddButton = findViewById<Button>(R.id.maxAddBtn)

        fun addData() {
            val minDataString = minEditText.text.toString()
            if (minDataString.isNotEmpty()) {
                try {
                    val minData = minDataString.toInt()
                    if (minData >= 0) {
                        minData.add(minData)
                        minEditText.text.clear()
                        Toast.makeText(
                            this,
                            "Data added successfully",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } catch (e: NumberFormatException) {
                    Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show()
                }
            }
        }
            fun maxAddData() {
                val maxDataString = maxEditText.text.toString()
                if (maxDataString.isNotEmpty()) {
                    try {
                        val maxData = maxDataString.toInt()
                        if (maxData >= 0) {
                            maxData.add(maxData)
                            maxEditText.text.clear()
                            Toast.makeText(
                                this,
                                "Data added successfully",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } catch (e: NumberFormatException) {
                        Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show()
                    }
                }
            }

                fun clearData() {
                    minData.clear()
                    maxData.clear()
                    Toast.makeText(this, "Data cleared", Toast.LENGTH_SHORT).show()
                }

                fun navigateToDetailedViewActivity() {
                    if (minData.isEmpty())
                        if (maxData.isEmpty()) {
                            Toast.makeText(this, "Please input data first", Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            val intent = Intent(this, DetailedViewActivity::class.java).apply {
                                putIntegerArrayListExtra("minData", ArrayList(minData))
                                putIntegerArrayListExtra("maxData", ArrayList(maxData))
                            }
                            startActivity(intent)
                        }
                }

                addButton.setOnClickListener {
                    addData()
                }
                maxAddButton.setOnClickListener {
                    maxAddData()
                }
                clearButton.setOnClickListener {
                    clearData()
                }

                btnView.setOnClickListener {
                    navigateToDetailedViewActivity()
                }
            }
        }