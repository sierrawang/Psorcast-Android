package org.sagebionetworks.research.psorcast.validation.ui.task_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_task_list.*
import org.sagebionetworks.research.domain.repository.TaskRepository
import org.sagebionetworks.research.mobile_ui.perform_task.PerformTaskActivity
import org.sagebionetworks.research.presentation.model.TaskView
import org.sagebionetworks.research.psorcast.validation.R
import java.util.*
import javax.inject.Inject

class TaskListFragment : DaggerFragment() {
    @Inject
    lateinit var taskRepository: TaskRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var result = inflater.inflate(R.layout.fragment_task_list, container, false)

        val recyclerView = result.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = GridLayoutManager(context, 1)
        val tasks = listOf(TaskItem("30 Second Walk", "1 minute", "Walk30Seconds"),
            TaskItem("Psoriasis Area Photo", "1 minute", "PsoriasisAreaPhoto"),
            TaskItem("Psoriasis Draw", "2 minutes", "PsoriasisDraw"),
            TaskItem("Painful Joint Count", "2 minutes", "PainfulJointCount"),
            TaskItem("Fingers Photo", "1 minute", "FingersPhoto"),
            TaskItem("Toes Photo", "1 minute", "ToesPhoto"),
            TaskItem("(For Dr.) Painful Joints", "1 minute", "PainfulJointsDoctor"),
            TaskItem("(For Dr.) Swollen Joints", "1 minute", "SwollenJointsDoctor"))
        recyclerView.adapter = TaskListAdapter(tasks) {
            launchTask(it.taskIdentifier, UUID.randomUUID())
        }

        return result
    }

    private fun launchTask(
        taskIdentifier: String,
        taskRunUUID: UUID?
    ) {
        val taskInfoView = taskRepository.getTaskInfo(taskIdentifier).blockingGet()

        //TODO: mapper
        val taskView = TaskView.builder().setIdentifier(taskInfoView.identifier).build()

        val intent = PerformTaskActivity.createIntent(requireContext(), taskView, taskRunUUID)
        this.startActivity(intent)
    }
}
