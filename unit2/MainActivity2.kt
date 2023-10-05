package com.example.unit2

import android.app.ProgressDialog
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class MainActivity2 : AppCompatActivity() {
    lateinit var ImageUrl : URL
    var `is` : InputStream? = null
    var bmImg: Bitmap? = null
    lateinit var imageView : ImageView
    lateinit var p: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val button = findViewById<Button>(R.id.button)
        imageView = findViewById(R.id.imageView)
        button.setOnClickListener{
            val asyncTask: AsyncTaskExample = AsyncTaskExample()
            asyncTask.execute("https://www.cleverfiles.com/howto/wp-content/uploads/2018/03/minion.jpg")

        }
    }
    private inner class AsyncTaskExample : AsyncTask<String?, String?, Bitmap?>() {

        override fun onPreExecute() {
            super.onPreExecute()
            p = ProgressDialog(this@MainActivity2)
            p.setMessage("Please wait...It is downloading")
            p.setCancelable(false)
            p.show()
        }

        override fun doInBackground(vararg p0: String?): Bitmap? {
            try {
                ImageUrl = URL(p0[0])
                val conn: HttpURLConnection =
                    ImageUrl.openConnection() as
                            HttpURLConnection
                conn.doInput = true
                conn.connect()
                `is` = conn.inputStream
                val options = BitmapFactory.Options()
                options.inPreferredConfig =
                    Bitmap.Config.RGB_565
                bmImg = BitmapFactory.decodeStream(`is`,null,options)
            }
            catch (e:IOException){
                e.printStackTrace()
            }
            return bmImg
        }

        override fun onPostExecute(result: Bitmap?) {
            super.onPostExecute(result)
            if (imageView != null){
                p.hide()
                imageView.setImageBitmap(result)
            }
            else{
                p.show()
            }
        }

    }
}