package com.subham.Basisswipeapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.subham.Basisswipeapp.Model.Remote.Network
import com.subham.Basisswipeapp.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         val strData= StringBuffer()

         CoroutineScope(Dispatchers.IO).launch {
            val data = Network.api.getData()


//             for (i in data.indices){
//                 if (!data[i].equals("/")){
//                     strData.append(data[i]).toString()
//                 }
//             }

             for(i in data.indices){
                 if (data[i] == '/'){
                     continue
                 }else{
                     strData.append(data[i])
                 }
             }
             Log.d("shubham",strData.toString())

         }
    }
}