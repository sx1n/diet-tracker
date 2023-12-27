package com.sx1n.diettracker.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.sx1n.diettracker.R
import com.sx1n.diettracker.databinding.FragmentPhysicalActivityRegistrationBinding
import com.sx1n.diettracker.utils.viewBinding

class PhysicalActivityRegistrationFragment : Fragment(R.layout.fragment_physical_activity_registration) {

    private val binding by viewBinding(FragmentPhysicalActivityRegistrationBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}