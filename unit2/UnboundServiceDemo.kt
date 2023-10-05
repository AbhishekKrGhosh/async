package com.example.unit2

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class UnboundServiceDemo : AppCompatActivity() {
    lateinit var start: Button
    lateinit var stop: Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_unbound_service_demo)
        start = findViewById(R.id.button2)
        stop = findViewById(R.id.button3)
        start.setOnClickListener{
            startService(Intent(this,MyUnboundServiceDemo::class.java))
        }
        stop.setOnClickListener {
            stopService(Intent(this, MyUnboundServiceDemo::class.java))
        }
    }
}