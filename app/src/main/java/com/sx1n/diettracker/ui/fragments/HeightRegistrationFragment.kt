package com.sx1n.diettracker.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sx1n.diettracker.R
import com.sx1n.diettracker.databinding.FragmentHeightRegistrationBinding
import com.sx1n.diettracker.databinding.FragmentPhysicalActivityRegistrationBinding
import com.sx1n.diettracker.utils.viewBinding

class HeightRegistrationFragment : Fragment(R.layout.fragment_height_registration) {

    private val binding by viewBinding(FragmentHeightRegistrationBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}