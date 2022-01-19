package com.subham.basisswipeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.subham.basisswipeapp.Model.Remote.Network
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         CoroutineScope(Dispatchers.IO).launch {
            val data =    Network.api.getData()
             Log.d("subham",data.toString())
         }
    }
}