package com.example.scheduledtodo.fragments.list

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.scheduledtodo.R
import com.example.scheduledtodo.data.TodoModel
import com.example.scheduledtodo.data.TodoViewModel
import kotlinx.android.synthetic.main.add_todo_dialog.view.*

class ListFragment : Fragment() {
    private val mTodoViewModel: TodoViewModel by viewModels()
    lateinit var listFragmentView: View
    lateinit var list: RecyclerView
    val adapter: TodoAdapter by lazy { TodoAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        listFragmentView = inflater.inflate(R.layout.fragment_list, container, false)
        list = listFragmentView.findViewById<RecyclerView>(R.id.today_todoList)
        setRecyclerView()
        mTodoViewModel.getAllData.observe(viewLifecycleOwner, object : Observer<List<TodoModel>> {
            override fun onChanged(data: List<TodoModel>?) {
                if (data != null) {
                    adapter.setTodoDataList(data)
                }
            }
        })
        return listFragmentView
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.today_list_menu, menu)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_todo -> {
                val addView = LayoutInflater.from(requireContext()).inflate(R.layout.add_todo_dialog, list, false)
                val addDialogBuilder = AlertDialog.Builder(requireContext())
                        .setView(addView)
                        .setTitle("ADD NEW TASK")
                        .setPositiveButton("Ok", object : DialogInterface.OnClickListener {
                            override fun onClick(dialog: DialogInterface?, which: Int) {
                                val title = addView.add_title_et.text.toString()
                                val description = addView.add_description_et.text.toString()
                                val schedule = addView.schedule_spinner.selectedItem.toString()
                                val newData = TodoModel(0, title, description, schedule)
                                mTodoViewModel.insertData(newData)
                            }

                        })
                        .setNegativeButton("Cancel") { _, _ ->
                            //処理なし
                        }
                addDialogBuilder.create().show()
            }

            R.id.delete_all -> {
                val deleteDialogBuilder = AlertDialog.Builder(requireContext())
                        .setTitle("DELETE ALL")
                        .setMessage("Are you sure you delete All items?")
                        .setPositiveButton("delete all", object : DialogInterface.OnClickListener {
                            override fun onClick(dialog: DialogInterface?, which: Int) {
                                mTodoViewModel.deleteAllData()
                                Toast.makeText(requireContext(),"Successfully delete all items", Toast.LENGTH_SHORT)
                                        .show()
                            }
                        })
                        .setNegativeButton("No") { _, _ ->
                            //処理なし
                        }
                deleteDialogBuilder.create().show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setRecyclerView() {
        list.adapter = adapter
        list.layoutManager = LinearLayoutManager(requireContext())
    }
}