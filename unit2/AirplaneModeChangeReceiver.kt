package com.example.unit2

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class AirplaneModeChangeReceiver:BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        val isAirplaneModeEnabled=p1?.getBooleanExtra("state",false)?: return
        if(isAirplaneModeEnabled){
            Toast.makeText(p0,"AirplaneModeEnabled",Toast.LENGTH_LONG).show()
        }
        else{

            Toast.makeText(p0, "AirplaneModeDisabled", Toast.LENGTH_LONG).show()
        }
    }

}