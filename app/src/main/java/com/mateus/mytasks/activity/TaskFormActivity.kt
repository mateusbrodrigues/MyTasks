package com.mateus.mytasks.activity

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.mateus.mytasks.databinding.ActivityTaskFormBinding
import com.mateus.mytasks.entity.Task
import com.mateus.mytasks.service.TaskService
import java.time.format.DateTimeFormatter
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeParseException

class TaskFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTaskFormBinding

    private val taskService: TaskService by viewModels()

    private var taskId: Long? = null

    private val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    private val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityTaskFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.e("lifecycle", "TaskForm onCreate")

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        taskId = intent.extras?.getLong("taskId")

        taskId?.let { id ->
            taskService.readById(id).observe(this) { responseDto ->
                if (responseDto.isError) {
                    Toast.makeText(this, "Erro ao carregar a tarefa", Toast.LENGTH_SHORT).show()
                } else {
                    responseDto.value?.let { task ->
                        binding.etTitle.setText(task.title)
                        binding.etDescription.setText(task.description)
                        binding.etDate.setText(task.date?.format(dateFormatter) ?: "")
                        binding.etTime.setText(task.time?.format(timeFormatter) ?: "")

                        if (task.completed) {
                            binding.btSave.visibility = View.INVISIBLE
                        }
                    }
                }
            }
        }

       /* intent.extras?.getString(Intent.EXTRA_TEXT)?.let { text ->
            binding.etTitle.setText(text)
            binding.etDescription.setText(text)
            binding.etDate.setText(text)
            binding.etTime.setText(text)
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }*/

        initComponents()
        setValues()
    }

    private fun initComponents() {

        binding.btSave.setOnClickListener {
            var isValid = true

            binding.tilTitle.error = null
            binding.etDate.error = null
            binding.etTime.error = null

            if (binding.etTitle.text.toString().isBlank()) {
                binding.tilTitle.error = "Campo obrigatório"
                isValid = false
            }

            val dateInput = binding.etDate.text.toString()
            val date: LocalDate? = if (dateInput.isNotBlank()) {
                try {
                    LocalDate.parse(dateInput, dateFormatter)
                } catch (e: DateTimeParseException) {
                    binding.tilDate.error = "Data inválida. Formato esperado: dd/MM/yyyy"
                    isValid = false
                    null
                }
            } else {
                null
            }

            val timeInput = binding.etTime.text.toString()
            val time: LocalTime? = if (timeInput.isNotBlank()) {
                try {
                    LocalTime.parse(timeInput, timeFormatter)
                } catch (e: DateTimeParseException) {
                    binding.tilTime.error = "Hora inválida. Formato esperado: HH:mm"
                    isValid = false
                    null
                }
            } else {
                null
            }

            if (!isValid) return@setOnClickListener

            val task = Task(
                title = binding.etTitle.text.toString(),
                description = binding.etDescription.text.toString(),
                date = date,
                time = time,
                id = taskId
            )

            taskService.save(task).observe(this) { responseDto ->
                if (responseDto.isError) {
                    Toast.makeText(this, "Erro com o servidor", Toast.LENGTH_SHORT).show()
                } else {
                    finish()
                }
            }
        }
    }

    @Suppress("deprecation")
    private fun setValues() {
        (intent.extras?.getSerializable("task") as Task?)?.let { task ->
            taskId = task.id
            binding.etTitle.setText(task.title)
            binding.etDescription.setText(task.description)
            binding.etDate.setText(task.date?.format(dateFormatter) ?: "")
            binding.etTime.setText(task.time?.format(timeFormatter) ?: "")
            if (task.completed) {
                binding.btSave.visibility = View.INVISIBLE
            }
        }
    }

    override fun onStart() {
        super.onStart()

        Log.e("lifecycle", "TaskForm onStart")
    }

    override fun onResume() {
        super.onResume()

        Log.e("lifecycle", "TaskForm onResume")
    }

    override fun onStop() {
        super.onStop()

        Log.e("lifecycle", "TaskForm onStop")
    }

    override fun onPause() {
        super.onPause()

        Log.e("lifecycle", "TaskForm onPause")
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.e("lifecycle", "TaskForm onDestroy")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }

        return super.onOptionsItemSelected(item)
    }
}