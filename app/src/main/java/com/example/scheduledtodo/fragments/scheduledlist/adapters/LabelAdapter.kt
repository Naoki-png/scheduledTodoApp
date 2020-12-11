package com.example.scheduledtodo.fragments.scheduledlist.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.scheduledtodo.R

class LabelAdapter(private val labelTxt: String): RecyclerView.Adapter<LabelAdapter.LabelViewHolder>() {
    class LabelViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val label = view.findViewById<TextView>(R.id.scheduled_day_label)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LabelViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.schedule_list_label, parent, false)
        return LabelViewHolder(view)
    }

    override fun getItemCount() = 1

    override fun onBindViewHolder(holder: LabelViewHolder, position: Int) {
        holder.label.text = labelTxt
    }
}