package com.example.scheduledtodo.fragments.scheduledlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.scheduledtodo.R
import com.example.scheduledtodo.data.TodoViewModel
import com.example.scheduledtodo.fragments.scheduledlist.adapters.ItemAdapter
import com.example.scheduledtodo.fragments.scheduledlist.adapters.LabelAdapter
import me.mvdw.recyclerviewmergeadapter.adapter.RecyclerViewMergeAdapter


class ScheduledListFragment : Fragment() {
    private val mTodoViewModel: TodoViewModel by viewModels()
    private val adapter: ItemAdapter by lazy { ItemAdapter() }
    lateinit var recyclerView: RecyclerView
    lateinit var mergeRecyclerAdapter: RecyclerViewMergeAdapter
    lateinit var labelList: List<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_scheduled_list, container, false)
        recyclerView = view.findViewById(R.id.scheduled_todoList)
        labelList = resources.getStringArray(R.array.scheduled_time).toList()
        setRecyclerView()

        mTodoViewModel.getSundayData.observe(viewLifecycleOwner, Observer { data ->
            adapter.setDataList(data)
        })

        return view
    }

    private fun setRecyclerView() {
        mergeRecyclerAdapter = RecyclerViewMergeAdapter()
        mergeRecyclerAdapter.addAdapter(LabelAdapter(labelList[0]))
        mergeRecyclerAdapter.addAdapter(adapter)
        recyclerView.adapter = mergeRecyclerAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }
}