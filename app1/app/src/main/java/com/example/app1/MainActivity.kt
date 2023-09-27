package com.example.app1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app1.databinding.ActivityMainBinding
import org.json.JSONArray
import java.io.File

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //取得するjsonファイルをassetフォルダから取得しておく
        val fileName = "sample_data.json"
        val inputStream = assets.open(fileName)
        val file = File(filesDir,fileName)
        if (!file.exists()){
            file.outputStream().use { inputStream.copyTo(it) }
        }
        val data = getData(file)
        val list = binding.list
        list.layoutManager = LinearLayoutManager(this)
        val adapter = ListAdapter(data)
        list.adapter = adapter


    }
    //jsonからデータを取得する
    fun getData(file: File):MutableList<ListData>{
        var dataList = mutableListOf<ListData>()
        val jsonStr = file.bufferedReader().use { it.readText() }
        val jsonArray  = JSONArray(jsonStr)
        for (i in 0 until jsonArray.length()){
            val jsonObject = jsonArray.getJSONObject(i)
            val name = jsonObject.getString("name")
            val age = jsonObject.getInt("age")
            val barth = jsonObject.getString("birthday")
            dataList.add(ListData(name, age, barth))
        }
        return dataList
    }
}
data class ListData(val name: String,val age: Int,val barth: String)