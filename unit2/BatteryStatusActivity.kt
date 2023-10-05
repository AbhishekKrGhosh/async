package com.example.unit2

import android.annotation.SuppressLint
import android.os.BatteryManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class BatteryStatusActivity : AppCompatActivity() {
    lateinit var battery: TextView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_battery_status)
        battery = findViewById(R.id.battery)

        val bm = applicationContext.getSystemService(BATTERY_SERVICE) as BatteryManager
        val batLevel:Int = bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
        if(batLevel<15){
            battery.text = "Battery is: ${batLevel.toString()} %\n kindly charge your phone"

        }
        else if(batLevel>=15 && batLevel<90){
            battery.text = "Battery is: ${batLevel.toString()} %"

        }
        else{
            battery.text = "Battery is: ${batLevel.toString()} %\n battery is enough"

        }


    }
}