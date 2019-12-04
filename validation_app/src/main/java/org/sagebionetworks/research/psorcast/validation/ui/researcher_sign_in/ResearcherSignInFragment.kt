package org.sagebionetworks.research.psorcast.validation.ui.researcher_sign_in

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.researcher_sign_in_fragment.*
import org.sagebionetworks.bridge.android.access.BridgeAccessViewModel
import org.sagebionetworks.research.psorcast.validation.R
import javax.inject.Inject

class ResearcherSignInFragment : DaggerFragment() {

    companion object {
        fun newInstance() =
            ResearcherSignInFragment()
    }

    @Inject
    lateinit var researchSignInViewModelFactory: ResearcherSignInViewModel.Factory

    private lateinit var researcherSignInViewModel: ResearcherSignInViewModel

    @Inject
    lateinit var bridgeAccessViewModelFactory: BridgeAccessViewModel.Factory

    private lateinit var bridgeAccessViewModel: BridgeAccessViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.researcher_sign_in_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        researcherSignInViewModel = ViewModelProviders.of(this, researchSignInViewModelFactory)
            .get(ResearcherSignInViewModel::class.java)
        // retrieve same instance of used by BridgeAccessViewModel MainFragment
        bridgeAccessViewModel =
            ViewModelProviders.of(requireActivity(), bridgeAccessViewModelFactory)
                .get(BridgeAccessViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onStart() {
        super.onStart()

        externalId.doOnTextChanged { text, start, count, after ->
            researcherSignInViewModel.setExternalId(text.toString())
        }

        signIn.setOnClickListener { researcherSignInViewModel.doSignIn() }
        researcherSignInViewModel.getIsLoadingLiveData().observe(this,
            Observer { value: Boolean? ->
                bridgeAccessViewModel.checkAccess()
            })
    }
}


