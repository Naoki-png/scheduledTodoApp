package com.example.scheduledtodo.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.scheduledtodo.R
import com.example.scheduledtodo.data.TodoModel
import kotlinx.android.synthetic.main.todo_list_row.view.*

class TodoAdapter: RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {
    var dataList = emptyList<TodoModel>()
    class TodoViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val title = view.title_tv
        val description = view.description_tv
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.todo_list_row, parent, false)
        return TodoViewHolder(view)
    }

    override fun getItemCount() = dataList.size

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.title.text = dataList[position].title
        holder.description.text = dataList[position].description
    }

    fun setTodoDataList(todoDataList: List<TodoModel>) {
        dataList = todoDataList
        notifyDataSetChanged()
    }
}