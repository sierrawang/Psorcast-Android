package org.sagebionetworks.research.psorcast.validation.ui.task_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
        return inflater.inflate(R.layout.fragment_task_list, container, false)
    }

    override fun onStart() {
        super.onStart()

        body_plaque_coverage.setOnClickListener {
            launchTask("PlaquesBodyMap", UUID.randomUUID())
        }

        joint_pain.setOnClickListener {
            launchTask("JointPain", UUID.randomUUID())
        }
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
