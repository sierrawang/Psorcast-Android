package org.sagebionetworks.research.psorcast.validation.ui.main

import org.sagebionetworks.bridge.android.access.BridgeAccessFragment
import org.sagebionetworks.research.psorcast.validation.R
import org.sagebionetworks.research.psorcast.validation.ui.participant_id.ParticipantIdFragment
import org.sagebionetworks.research.psorcast.validation.ui.researcher_sign_in.ResearcherSignInFragment
import org.sagebionetworks.research.psorcast.validation.ui.task_list.TaskListFragment
import org.slf4j.LoggerFactory

class MainFragment : BridgeAccessFragment() {

    companion object {
        fun newInstance() = MainFragment()

        private val LOGGER = LoggerFactory.getLogger(MainFragment::class.java)
    }

    override fun onAccessGranted() {
        childFragmentManager.beginTransaction()
            .replace(R.id.container, ParticipantIdFragment())
            .commit()
    }

    override fun onRequireAuthentication() {
        LOGGER.debug("Authentication required: showing ResearcherSignInFragment")

        val fragment = childFragmentManager.findFragmentByTag("ResearcherSignInFragment")
        if (fragment?.isVisible == true ) {
            return
        }
        childFragmentManager.beginTransaction()
            .replace(R.id.container, ResearcherSignInFragment.newInstance(), "ResearcherSignInFragment")
            .commit()
    }

    override fun onRequireConsent() {
        TODO("not implemented")
    }

}
