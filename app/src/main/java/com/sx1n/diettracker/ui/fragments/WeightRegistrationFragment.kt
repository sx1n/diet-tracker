package com.sx1n.diettracker.ui.fragments

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.sx1n.diettracker.R
import com.sx1n.diettracker.data.viewModels.RegisterViewModel
import com.sx1n.diettracker.databinding.FragmentWeightRegistrationBinding
import com.sx1n.diettracker.ui.listeners.OnStepChangedListener
import com.sx1n.diettracker.utils.viewBinding

class WeightRegistrationFragment : Fragment(R.layout.fragment_weight_registration) {

    private lateinit var stepChangedListener: OnStepChangedListener

    private val binding by viewBinding(FragmentWeightRegistrationBinding::bind)

    private var weight = 0.0
    private lateinit var editWeight: EditText

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

        val minValue = 30.0
        val maxValue = 220.0

        val errorText = binding.tvError

        editWeight = binding.editWeight

        observe()

        binding.btnNext.setOnClickListener {
            viewModel.registerWeight(weight)
            findNavController().navigate(R.id.action_weightRegistrationFragment_to_physicalActivityRegistrationFragment)
        }

        editWeight.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(editable: Editable?) {
                val enteredValue = editable.toString().toDoubleOrNull()

                if (editable.isNullOrEmpty() || (enteredValue != null && !isInRange(
                        minValue,
                        maxValue,
                        enteredValue
                    ))
                ) {
                    errorText.visibility = View.VISIBLE
                    disableButton()
                    return
                } else {
                    errorText.visibility = View.INVISIBLE
                    if (enteredValue?.let { hasMoreThanOneDecimalPlace(it) } == true) {
                        val length = enteredValue.toString().length
                        editable.delete(length - 1, length)
                    }
                    weight = editable.toString().toDouble()
                    enableButton()
                }
            }
        })

    }

    private fun notifyStepChanged() {
        stepChangedListener.onStepChanged(4)
    }

    private fun observe() {
        viewModel.weight.observe(viewLifecycleOwner) { weight ->
            editWeight.setText(weight.toString())
        }
    }

    private fun isInRange(min: Double, max: Double, value: Double): Boolean {
        return value in min..max
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

    private fun hasMoreThanOneDecimalPlace(number: Double): Boolean {
        val numberString = number.toString()
        val indexDot = numberString.indexOf('.')
        return indexDot > 0 && indexDot < numberString.length - 2
    }
}