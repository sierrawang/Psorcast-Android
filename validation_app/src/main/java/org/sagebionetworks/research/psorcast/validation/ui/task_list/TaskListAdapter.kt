package org.sagebionetworks.research.psorcast.validation.ui.task_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import org.sagebionetworks.research.psorcast.validation.R

class TaskListAdapter(val tasks: List<TaskItem>, val listener: (TaskItem) -> Unit) : RecyclerView.Adapter<TaskViewHolder>() {
    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) = holder.bind(tasks[position], listener)

    override fun getItemCount() = tasks.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : TaskViewHolder {
        val taskView = LayoutInflater.from(parent.context).inflate(R.layout.task_item_view, parent, false) as ConstraintLayout
        return TaskViewHolder(taskView)
    }
}