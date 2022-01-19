package com.subham.Basisswipeapp.ui

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.subham.Basisswipeapp.DataItem
import com.subham.Basisswipeapp.Model.Remote.Network
import com.subham.Basisswipeapp.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject


class MainActivity : AppCompatActivity() {
    lateinit var adapter: CardAdapter
    private var mutableDataFromApi = java.util.<List<DataItem>>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val strData = StringBuffer()


        CoroutineScope(Dispatchers.IO).launch {
            val data = Network.api.getData()


//             for (i in data.indices){
//                 if (!data[i].equals("/")){
//                     strData.append(data[i]).toString()
//                 }
//             }


            //Removing "/" from the api
            for (i in data.indices) {
                if (data[i] == '/') {
                    continue
                } else {
                    strData.append(data[i])
                }
            }
            Log.d("shubham", strData.toString())

            try {
                /*
                Build a JSON object from the received string
                 */

                val jsonObject = JSONObject(strData.toString())
                val jsonArrayData = jsonObject.getJSONArray("data")
                Log.d(TAG, "buildResponseModel: ${jsonArrayData.get(0)}")
                val dataList: ArrayList<DataItem> = ArrayList()
                for (i in 0 until jsonArrayData.length()) {
                    val dataResponse = jsonArrayData.getJSONObject(i)
                    val id = dataResponse.getString("id")
                    val text = dataResponse.getString("text")
                    val data = DataItem(id, text)
                    dataList.add(data)


                }
                //checking if the api is working or not
                for(i in dataList) {
                    Log.d("shubham", "onCreate: ${i.id}")
                }
                Log.d("shubham", dataList.toString())

                mutableDataFromApi.postValue(dataList)


            } catch (e: JSONException) {
                Log.d("shubham", e.toString())
            }


        }
        setrecycleview()




    }

    private fun setrecycleview() {
              recycleView.apply {

                  adapter = CardAdapter(mutableDataFromApi,this@MainActivity)
                  layoutManager=LinearLayoutManager(applicationContext)
              }
    }


}






