package com.mateus.mytasks.adapter

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.RecyclerView
import com.mateus.mytasks.R
import com.mateus.mytasks.databinding.TaskListItemBinding
import com.mateus.mytasks.entity.Task
import com.mateus.mytasks.listener.TaskItemClickListener
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

class TaskViewHolder(
    private val context: Context,
    private val binding: TaskListItemBinding,
    private val listener: TaskItemClickListener
) : RecyclerView.ViewHolder(binding.root) {

    fun setValues(task: Task) {
        binding.tvTitle.text = task.title

        val dateNow = LocalDate.now()

        task.date?.let { date ->
            when {
                task.completed -> binding.statusIndicator.setBackgroundResource(R.color.green_700)
                date == dateNow -> binding.statusIndicator.setBackgroundResource(R.color.orange_200)
                date < dateNow -> binding.statusIndicator.setBackgroundResource(R.color.red)
                else -> binding.statusIndicator.setBackgroundResource(R.color.blue_700)
            }
        } ?: run {
            if (task.completed) {
                binding.statusIndicator.setBackgroundResource(R.color.green_700)
            }else{
                binding.statusIndicator.setBackgroundResource(R.color.blue_700)
            }
        }

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val useExpandedFormat = sharedPreferences.getBoolean("date_format_preference", false)

        binding.tvDate.text = task.date?.let { date ->
            if (useExpandedFormat) {
                date.format(DateTimeFormatter.ofPattern("d 'de' MMMM 'de' yyyy", Locale("pt", "BR")))
            } else {
                date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
            }
        } ?: run {
            "-"
        }

        binding.tvTime.text = task.time?.let {
            task.time.toString()
        } ?: run {
            "-"
        }

        binding.root.setOnClickListener {
            listener.onClick(task)
        }

        binding.root.setOnCreateContextMenuListener { menu, _, _ ->
            menu.add(ContextCompat.getString(context, R.string.mark_as_completed)).setOnMenuItemClickListener {
                listener.onMarkAsCompleteClick(adapterPosition, task)
                true
            }
            menu.add(ContextCompat.getString(context, R.string.share)).setOnMenuItemClickListener {
                listener.onShareClick(task)
                true
            }
        }
    }
}