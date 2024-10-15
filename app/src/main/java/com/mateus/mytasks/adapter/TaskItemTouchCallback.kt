package com.mateus.mytasks.adapter

import android.util.Log
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.mateus.mytasks.listener.TaskItemSwipeListener

class TaskItemTouchCallback(private val listener: TaskItemSwipeListener) : ItemTouchHelper.Callback() {

    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
        return makeMovementFlags(
            0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        )
    }

    override fun onMove(
        recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder
    ): Boolean {
        Log.d("touch", "Item arrastado")
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        Log.d("touch", "Item deslizado")

        listener.onSwipe(viewHolder.adapterPosition)
    }
}