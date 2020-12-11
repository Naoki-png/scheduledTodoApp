package com.example.scheduledtodo.fragments.scheduledlist.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.scheduledtodo.R
import com.example.scheduledtodo.data.TodoModel

class ItemAdapter(): RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    private var dataList: List<TodoModel> = emptyList<TodoModel>()

    class ItemViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val title = view.findViewById<TextView>(R.id.schedule_title_tv)
        val description = view.findViewById<TextView>(R.id.schedule_description_tv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.schedule_list_row, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount() = dataList.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.title.text = dataList[position].title
        holder.description.text = dataList[position].description
    }

    fun setDataList(todoList: List<TodoModel>) {
        dataList = todoList
        notifyDataSetChanged()
    }
}