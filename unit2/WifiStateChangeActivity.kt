package com.example.unit2

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.WifiManager
import android.os.Bundle
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class WifiStateChangeActivity : AppCompatActivity() {
    lateinit var wifiSwitch: Switch
    lateinit var wifiManager: WifiManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wifi_state_change)
        wifiSwitch = findViewById(R.id.switch1)
        wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        wifiSwitch.setOnCheckedChangeListener{
            _, isChecked ->
            if(isChecked){
                wifiManager.isWifiEnabled = true
                wifiSwitch.text = "WiFi is On"
            }
            else{
                wifiManager.isWifiEnabled = false
                wifiSwitch.text = "Wifi is Off"
            }
        }

    }

    @SuppressLint("SuspiciousIndentation")
    override fun onStart() {
        super.onStart()
        val intentFilter = IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION)
            registerReceiver(wifiStateReceiver, intentFilter)

    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(wifiStateReceiver)
    }
    private val wifiStateReceiver: BroadcastReceiver = object :
    BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            when (intent?.getIntExtra(
                WifiManager.EXTRA_WIFI_STATE,
                WifiManager.WIFI_STATE_UNKNOWN
            )){
                WifiManager.WIFI_STATE_ENABLED -> {
                    wifiSwitch.isChecked = true
                    wifiSwitch.text = "WiFi if On"
                    Toast.makeText(this@WifiStateChangeActivity, "Wifi is On", Toast.LENGTH_LONG).show()
                }
                WifiManager.WIFI_STATE_DISABLED ->{
                    wifiSwitch.isChecked  =false
                    wifiSwitch.text = "Wifi is Off"
                    Toast.makeText(this@WifiStateChangeActivity, "Wifi os Off", Toast.LENGTH_LONG).show()

                }
            }
        }
    }

}