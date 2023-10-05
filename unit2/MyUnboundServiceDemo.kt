package com.example.unit2

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.provider.Settings

class MyUnboundServiceDemo:Service() {
    lateinit var mp:MediaPlayer
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        mp=MediaPlayer.create(this,Settings.System.DEFAULT_ALARM_ALERT_URI)
        mp.start()
        mp.isLooping = true
        return START_STICKY
    }
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        mp.stop()
    }

    override fun stopService(name: Intent?): Boolean {

        return super.stopService(name)
    }
}