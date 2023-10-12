package com.example.co5225

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.co5225.databinding.ActivityMainBinding
import java.io.IOException
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import java.util.Scanner

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnQuote.setOnClickListener {
            handleRetrieveQuoteWithVolley()
        }
    }

//    fun getQuote(textView: View) {
//        val thread = Thread {
//            val url = URL("https://api.chucknorris.io/jokes/random")
//
//            try {
//                val connection = url.openConnection() as HttpURLConnection
//                connection.connectTimeout = 10000
//                connection.readTimeout = 10000
//                connection.requestMethod = "GET"
//                connection.connect()
//
//                val responseCode = connection.responseCode
//
//                if (responseCode == HttpURLConnection.HTTP_OK) {
//                    val scanner = Scanner(connection.inputStream)
//                    scanner.useDelimiter("\\A")
//
//                    val jsonData = if (scanner.hasNext()) scanner.next() else ""
//                    val quote = parseJson(jsonData)
//
//                    updateTextBoxFromThread(quote)
//                } else {
//                    updateTextBoxFromThread("Sorry, there's a problem with the server")
//                }
//            } catch (e: IOException) {
//                updateTextBoxFromThread("Sorry, there was an error processing the data")
//            }
//        }
//
//        thread.start()
//    }


    private fun parseJson(jsonData: String?): String {
        val jsonObject = JSONObject(jsonData)
        val quote = jsonObject.getString("value")
        return quote

    }

//    private fun updateTextBoxFromThread(text: String) {
//
//        runOnUiThread {
//            binding.textView.text = text
//
//        }
//
//    }

    private fun handleRetrieveQuoteWithVolley() {
        val queue = Volley.newRequestQueue(this)
        val jsonObjectRequest = JsonArrayRequest(
            Request.Method.GET, "https://api.chucknorris.io/jokes/random", null,{
                    response -> binding.textView.text = parseJson(response.toString()) },

            { binding.textView.text = "That didn't work!" + ": $it"}

        )
        queue.add(jsonObjectRequest)
    }
}


