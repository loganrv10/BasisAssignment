package com.subham.Basisswipeapp.ui

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.subham.Basisswipeapp.DataItem
import com.subham.Basisswipeapp.Model.Remote.Network
import com.subham.Basisswipeapp.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Response
import org.json.JSONException
import org.json.JSONObject
import java.lang.Character.toString
import java.lang.Integer.toString
import java.util.Objects.toString


class MainActivity : AppCompatActivity() {

    private var mutableDataFromApi = MutableLiveData<List<DataItem>>()
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

                for(i in dataList) {
                    Log.d("shubham", "onCreate: ${i.id}")
                }
                Log.d("shubham", dataList.toString())

                mutableDataFromApi.postValue(dataList)

                /*
                After the response class  is built, We need to update the UI so we have to communicate with the UI thread
                and display the data in the main thread.
                 */

                // This method will be called in the main thread
            } catch (e: JSONException) {
                Log.d("shubham", e.toString())
            }
//             buildResponseModel(strData);

        }


    }
}



