package com.sx1n.diettracker.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.kevalpatel2106.rulerpicker.RulerValuePicker
import com.kevalpatel2106.rulerpicker.RulerValuePickerListener
import com.sx1n.diettracker.R
import com.sx1n.diettracker.data.viewModels.RegisterViewModel
import com.sx1n.diettracker.databinding.FragmentHeightRegistrationBinding
import com.sx1n.diettracker.ui.listeners.OnStepChangedListener
import com.sx1n.diettracker.utils.viewBinding

class HeightRegistrationFragment : Fragment(R.layout.fragment_height_registration) {

    private lateinit var stepChangedListener: OnStepChangedListener

    private val binding by viewBinding(FragmentHeightRegistrationBinding::bind)

    private lateinit var rulerValuePicker: RulerValuePicker
    private lateinit var heightEditText: EditText

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

        rulerValuePicker = binding.rulerPicker
        heightEditText = binding.editHeight

        rulerValuePicker.selectValue(150)

        observe()

        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_heightRegistrationFragment_to_weightRegistrationFragment)
        }

        rulerValuePicker.setValuePickerListener(object : RulerValuePickerListener {
            override fun onValueChange(value: Int) {
                heightEditText.setText(value.toString())
                viewModel.registerHeight(value)
                enableButton()
            }

            override fun onIntermediateValueChange(selectedValue: Int) {
                heightEditText.setText(selectedValue.toString())
                disableButton()
            }
        })


    }

    private fun notifyStepChanged() {
        stepChangedListener.onStepChanged(3)
    }

    private fun observe() {
        viewModel.height.observe(viewLifecycleOwner) { height ->
            if (height > 0) rulerValuePicker.selectValue(height)
        }
    }

    private fun disableButton() {
        val btnNext = binding.btnNext
        btnNext.isClickable = false
        btnNext.isEnabled = false
        btnNext.isLongClickable = false
        btnNext.setTextColor(ContextCompat.getColor(requireContext(), R.color.opaque_white))
    }

    private fun enableButton() {
        val btnNext = binding.btnNext
        btnNext.isClickable = true
        btnNext.isEnabled = true
        btnNext.isLongClickable = true
        btnNext.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
    }

}