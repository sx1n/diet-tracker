package com.sx1n.diettracker.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sx1n.diettracker.R
import com.sx1n.diettracker.databinding.FragmentGenderRegistrationBinding
import com.sx1n.diettracker.databinding.FragmentPhysicalActivityRegistrationBinding
import com.sx1n.diettracker.ui.listeners.OnStepChangedListener
import com.sx1n.diettracker.utils.viewBinding

class GenderRegistrationFragment : Fragment(R.layout.fragment_gender_registration) {

    private val binding by viewBinding(FragmentGenderRegistrationBinding::bind)

    private var stepChangedListener: OnStepChangedListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnStepChangedListener) {
            stepChangedListener = context
        } else {
            throw ClassCastException("$context must implement OnStepChangedListener")
        }

        notifyStepChanged(2)
    }

    // Chame este método quando o passo for alterado no Fragment
    private fun notifyStepChanged(step: Int) {
        stepChangedListener?.onStepChanged(step)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}