package com.mateus.mytasks.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.mateus.mytasks.R
import com.mateus.mytasks.databinding.TaskListItemBinding
import com.mateus.mytasks.entity.Task
import com.mateus.mytasks.listener.TaskItemClickListener

class TasksAdapter(
    private val context: Context,
    private val messageView: TextView,
    private val listener: TaskItemClickListener
) : RecyclerView.Adapter<TaskViewHolder>() {

    private val tasks = ArrayList<Task>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        Log.e("adapter", "Criando um TaskViewHolder")

        val binding = TaskListItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return TaskViewHolder(context, binding, listener)
    }

    override fun getItemCount() = tasks.size

    override fun onBindViewHolder(taskViewHolder: TaskViewHolder, position: Int) {
        Log.e("adapter", "Populando um TaskViewHolder")

        val task = tasks[position]
        taskViewHolder.setValues(task)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: List<Task>) {
        tasks.clear()
        tasks.addAll(items)

        notifyDataSetChanged()
        checkEmptyTasks()
    }

    fun getItem(position: Int): Task {
        return tasks[position]
    }

    fun refreshItem(position: Int) {
        notifyItemChanged(position)
    }

    fun deleteItem(position: Int) {
        tasks.removeAt(position)
        notifyItemRemoved(position)

        checkEmptyTasks()
    }

    fun updateItem(position: Int, item: Task) {
        tasks[position] = item
        notifyItemChanged(position)
    }

    private fun checkEmptyTasks() {
        if (tasks.isEmpty()) {
            messageView.text = ContextCompat.getString(context, R.string.empty_tasks)
        } else {
            messageView.text = null
        }
    }
}