package com.example.app1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app1.databinding.ListItemBinding

class ListAdapter(private val dataList: MutableList<ListData>):RecyclerView.Adapter<ListAdapter.ListViewHolder>() {
    inner class ListViewHolder(private val binding: ListItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bindData(data: ListData){
            binding.listName.text = data.name
            binding.listAge.text = data.age.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = dataList[position]
        holder.bindData(data)
    }
}