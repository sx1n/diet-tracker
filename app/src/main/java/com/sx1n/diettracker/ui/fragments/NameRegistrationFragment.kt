package com.sx1n.diettracker.ui.fragments

import android.content.Context
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat.getColor
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.sx1n.diettracker.R
import com.sx1n.diettracker.data.viewModels.RegisterViewModel
import com.sx1n.diettracker.databinding.FragmentNameRegistrationBinding
import com.sx1n.diettracker.ui.listeners.OnStepChangedListener
import com.sx1n.diettracker.utils.viewBinding

class NameRegistrationFragment : Fragment(R.layout.fragment_name_registration) {

    private lateinit var stepChangedListener: OnStepChangedListener

    private val binding by viewBinding(FragmentNameRegistrationBinding::bind)

    private lateinit var firstnameTextInputLayout: TextInputLayout
    private lateinit var firstnameTextInput: TextInputEditText
    private lateinit var lastnameTextInputLayout: TextInputLayout
    private lateinit var lastnameTextInput: TextInputEditText

    private val viewModel: RegisterViewModel by navGraphViewModels(R.id.register_navigation)

    private var firstnameHasError = true
    private var lastnameHasError = true

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

        setupUi()

        binding.btnNext.setOnClickListener {
            val firstName = firstnameTextInput.text.toString()
            val lastName = lastnameTextInput.text.toString()

            viewModel.registerName(firstName, lastName)
            findNavController().navigate(R.id.action_nameRegistrationFragment_to_genderRegistrationFragment)
        }

        firstnameTextInput.doOnTextChanged { text, _, _, _ ->
            if (text!!.length >= 20) {
                firstnameTextInputLayout.error = getString(R.string.character_limit_reached)
                firstnameTextInputLayout.counterTextColor = ColorStateList.valueOf(
                    getColor(
                        requireContext(),
                        R.color.red_300
                    )
                )
                firstnameHasError = true
                updateButtonStatus()
                return@doOnTextChanged
            } else if (text.length > 16) {
                firstnameTextInputLayout.isCounterEnabled = true
                firstnameTextInputLayout.counterTextColor = ColorStateList.valueOf(
                    getColor(
                        requireContext(),
                        R.color.yellow_300
                    )
                )
                firstnameTextInputLayout.error = null
                firstnameHasError = false
                updateButtonStatus()
                return@doOnTextChanged
            } else if (text.isNotEmpty()) {
                firstnameTextInputLayout.isCounterEnabled = false
                firstnameTextInputLayout.error = null
                firstnameTextInputLayout.helperText = null
                firstnameHasError = false
                updateButtonStatus()
            } else if (text.isEmpty()) {
                firstnameTextInputLayout.error = getString(R.string.required)
                firstnameHasError = true
                updateButtonStatus()
            }
        }

        lastnameTextInput.doOnTextChanged { text, _, _, _ ->
            if (text!!.length >= 40) {
                lastnameTextInputLayout.error = getString(R.string.character_limit_reached)
                lastnameTextInputLayout.counterTextColor = ColorStateList.valueOf(
                    getColor(
                        requireContext(),
                        R.color.red_300
                    )
                )
                lastnameHasError = true
                updateButtonStatus()
                return@doOnTextChanged
            } else if (text.length > 36) {
                lastnameTextInputLayout.isCounterEnabled = true
                lastnameTextInputLayout.counterTextColor = ColorStateList.valueOf(
                    getColor(
                        requireContext(),
                        R.color.yellow_300
                    )
                )
                lastnameTextInputLayout.error = null
                lastnameHasError = false
                updateButtonStatus()
                return@doOnTextChanged
            } else if (text.isNotEmpty()) {
                lastnameTextInputLayout.isCounterEnabled = false
                lastnameTextInputLayout.error = null
                lastnameTextInputLayout.helperText = null
                lastnameHasError = false
                updateButtonStatus()
            } else if (text.isEmpty()) {
                lastnameTextInputLayout.error = getString(R.string.required)
                lastnameHasError = true
                updateButtonStatus()
            }
        }

    }

    private fun notifyStepChanged() {
        stepChangedListener.onStepChanged(1)
    }

    private fun setupUi() {
        firstnameTextInputLayout = binding.firstnameTextInputLayout
        firstnameTextInput = binding.firstnameTextInput

        lastnameTextInputLayout = binding.lastnameTextInputLayout
        lastnameTextInput = binding.lastnameTextInput

        viewModel.firstName.observe(viewLifecycleOwner) { newFirstName ->
            firstnameTextInput.setText(newFirstName)
        }

        viewModel.lastName.observe(viewLifecycleOwner) { newLastName ->
            lastnameTextInput.setText(newLastName)
        }
    }

    private fun updateButtonStatus() {
        if (firstnameHasError || lastnameHasError) {
            disableButton()
        } else {
            enableButton()
        }
    }

    private fun disableButton() {
        val btnNext = binding.btnNext
        btnNext.isClickable = false
        btnNext.isEnabled = false
        btnNext.isLongClickable = false
        btnNext.setTextColor(getColor(requireContext(), R.color.opaque_white))
    }

    private fun enableButton() {
        val btnNext = binding.btnNext
        btnNext.isClickable = true
        btnNext.isEnabled = true
        btnNext.isLongClickable = true
        btnNext.setTextColor(getColor(requireContext(), R.color.white))
    }
}