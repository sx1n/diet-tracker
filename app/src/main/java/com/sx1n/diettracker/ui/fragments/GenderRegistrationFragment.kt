package com.sx1n.diettracker.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.sx1n.diettracker.R
import com.sx1n.diettracker.data.viewModels.RegisterViewModel
import com.sx1n.diettracker.databinding.FragmentGenderRegistrationBinding
import com.sx1n.diettracker.ui.listeners.OnStepChangedListener
import com.sx1n.diettracker.utils.viewBinding

class GenderRegistrationFragment : Fragment(R.layout.fragment_gender_registration) {

    private lateinit var stepChangedListener: OnStepChangedListener

    private val binding by viewBinding(FragmentGenderRegistrationBinding::bind)

    private val viewModel: RegisterViewModel by navGraphViewModels(R.id.register_navigation)

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

        observe()

        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_genderRegistrationFragment_to_heightRegistrationFragment)
        }

        binding.rgSex.setOnCheckedChangeListener { _, checkedId ->
            val radioButton = binding.root.findViewById<RadioButton>(checkedId)
            val sex: Char = if (radioButton == binding.rbMale) 'M' else 'F'

            viewModel.registerSex(sex)
        }
    }

    private fun notifyStepChanged() {
        stepChangedListener.onStepChanged(2)
    }

    private fun enableButton() {
        val btnNext = binding.btnNext
        btnNext.isClickable = true
        btnNext.isEnabled = true
        btnNext.isLongClickable = true
        btnNext.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
    }

    private fun observe() {
        viewModel.sex.observe(viewLifecycleOwner) { sex ->
            if (sex == 'M') {
                binding.rbMale.isChecked = true
            } else {
                binding.rbFemale.isChecked = true
            }
            enableButton()
        }
    }
}