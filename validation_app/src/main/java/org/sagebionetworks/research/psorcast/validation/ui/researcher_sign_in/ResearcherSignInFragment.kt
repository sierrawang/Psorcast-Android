package org.sagebionetworks.research.psorcast.validation.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import org.sagebionetworks.research.psorcast.validation.R

class ResearcherSignInFragment : Fragment() {

    companion object {
        fun newInstance() = ResearcherSignInFragment()
    }

    private lateinit var viewModel: ResearcherSignInViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.researcher_sign_in_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ResearcherSignInViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
