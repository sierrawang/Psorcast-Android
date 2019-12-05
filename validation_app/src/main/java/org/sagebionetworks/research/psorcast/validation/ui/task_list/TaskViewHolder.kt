package org.sagebionetworks.research.psorcast.validation.ui.task_list

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.task_item_view.view.*

class TaskViewHolder(private val taskView: ConstraintLayout) : RecyclerView.ViewHolder(taskView) {

    val title = taskView.task_title
    val time = taskView.task_time
    val button = taskView.begin_button

    fun bind(item: TaskItem, listener: (TaskItem) -> Unit) = with(taskView) {
        title.text = item.title
        time.text = item.time
        button.setOnClickListener { listener(item) }
    }
}
