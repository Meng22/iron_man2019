package com.example.mrtstation

import android.app.AlertDialog
import android.app.DownloadManager
import android.app.VoiceInteractor
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.service.autofill.Dataset
import android.util.JsonReader
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import org.json.JSONArray
import java.io.IOException
import java.util.concurrent.TimeUnit
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    private var itemList = ArrayList<Data.datas>()
    private lateinit var adapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "2019 鐵人賽"


        adapter = MyAdapter()
        recycleview.layoutManager = LinearLayoutManager(this)
        recycleview.adapter = adapter

        val request = Request.Builder()
            .url("https://goodideas-studio-v3-ironman.appspot.com")
            .build()
        val call = OkHttpClient.Builder()
            .connectTimeout(20,TimeUnit.SECONDS)
            .readTimeout(30,TimeUnit.SECONDS)
            .build()
        call.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
            }
            override fun onResponse(call: Call, response: Response) {
                val responseData = response.body()!!.string()
                val DataList = Gson().fromJson(responseData, Array<Data>::class.java).toList()
                val Data = DataList[0]
                for (i in 0 until Data.web_camp.size){
                    itemList.add(Data.web_camp[i])
                }
                runOnUiThread {
                    adapter.updateList(itemList)
                }
            }
        })
    }
}
