package com.example.unit2

import android.annotation.SuppressLint
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var pb: ProgressBar
    lateinit var lv:ListView
    var ar= arrayOf("1","2","3","4","5","6","7","8","9","10")
    lateinit var ad:ArrayAdapter<String>
    lateinit var al:ArrayList<String>
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pb=findViewById(R.id.pb)
        lv=findViewById(R.id.lv)
        al= ArrayList()
        ad= ArrayAdapter(this,
        android.R.layout.simple_list_item_1,al)
        lv.adapter=ad
        MyTaskDemo().execute()
    }
    internal inner class MyTaskDemo: AsyncTask<Void?,Int?,String>() {

        override fun onPreExecute() {
            super.onPreExecute()
            pb.max=10
            pb.progress=0
            pb.visibility= View.VISIBLE

        }
        override fun doInBackground(vararg p0: Void?): String? {
            for(i in 1 .. 10)
            {
                publishProgress(i)
            }
            try {
                Thread.sleep(1000)
            }
            catch (e:java.lang.Exception){
                e.printStackTrace()
            }
            return "Task Completed"
        }

        override fun onProgressUpdate(vararg values: Int?) {
            //super.onProgressUpdate(*values)
            pb.progress=values[0]!!
            al.add(ar[values[0]!!-1])
            ad.notifyDataSetChanged()
        }

        override fun onPostExecute(result: String?) {
            //super.onPostExecute(result)
            Toast.makeText(this@MainActivity, result,Toast.LENGTH_LONG).show()
        }
    }
}