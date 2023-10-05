package com.example.unit2

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.view.View
import android.widget.Button
import android.widget.TextView

class BoundServiceActivity : AppCompatActivity() {
    var mBoundService: BoundServiceDemo? = null
    var mServiceBound = false
    lateinit var printTimestampButton: Button
    lateinit var stopServiceButton : Button
    lateinit var timestampText : TextView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bound_service)
        printTimestampButton = findViewById(R.id.button6)
        stopServiceButton = findViewById(R.id.button7)
        timestampText = findViewById(R.id.textView2)
        printTimestampButton.setOnClickListener {
            if(mServiceBound) {
                timestampText.text = mBoundService!!.getTimestamp()
            }
        }
        stopServiceButton.setOnClickListener {
            if(mServiceBound) {
                unbindService(mServiceConnection)
                mServiceBound = false
            }
            val intent = Intent(
                this@BoundServiceActivity,
                BoundServiceDemo::class.java
            )
            stopService(intent)
        }

    }

    override fun onStart() {
        super.onStart()
        val intent = Intent(this, BoundServiceDemo::class.java)
        startService(intent)
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE)
    }

    override fun onStop() {
        super.onStop()
        if(mServiceBound){
            unbindService(mServiceConnection)
            mServiceBound = false
        }
    }
    val mServiceConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            mServiceBound = false
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val myBinder: BoundServiceDemo.MyBinder = service as
                    BoundServiceDemo.MyBinder
            mBoundService = myBinder.getService()
            mServiceBound = true
        }
    }
}