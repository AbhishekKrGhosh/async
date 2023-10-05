package com.example.unit2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.content.ContextCompat

class ForegroundServiceActivityDemo : AppCompatActivity() {
    lateinit var start: Button
    lateinit var stop: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_foreground_service_demo)
        start = findViewById(R.id.button4)
        stop = findViewById(R.id.button5)
        start.setOnClickListener {
            val intent = Intent(this,ForegroundDemo::class.java)
            intent.putExtra("inputExtra","Foreground Service is running ...")
            ContextCompat.startForegroundService(this,intent)

        }
        stop.setOnClickListener {
            val intent = Intent(this,ForegroundDemo::class.java)
            stopService(intent)
        }
    }
}