package com.sx1n.diettracker.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.sx1n.diettracker.R
import com.sx1n.diettracker.databinding.FragmentPhysicalActivityRegistrationBinding
import com.sx1n.diettracker.ui.listeners.OnStepChangedListener
import com.sx1n.diettracker.utils.viewBinding

class PhysicalActivityRegistrationFragment : Fragment(R.layout.fragment_physical_activity_registration) {

    private lateinit var stepChangedListener: OnStepChangedListener

    private val binding by viewBinding(FragmentPhysicalActivityRegistrationBinding::bind)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnStepChangedListener) {
            stepChangedListener = context
        } else {
            throw ClassCastException("$context must implement OnStepChangedListener")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        notifyStepChanged()
    }

    private fun notifyStepChanged() {
        stepChangedListener.onStepChanged(5)
    }
}