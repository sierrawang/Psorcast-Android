package org.sagebionetworks.research.psorcast.validation.ui.participant_id

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.participant_id.*
import org.sagebionetworks.research.psorcast.validation.R
import org.sagebionetworks.research.psorcast.validation.ui.task_list.TaskListFragment

class ParticipantIdFragment : DaggerFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.participant_id, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        submit_button.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction()
                .replace(R.id.participant_id_fragment, TaskListFragment())
                .commit()
            it.visibility = View.GONE
        }
    }
}