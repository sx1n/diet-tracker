package com.sx1n.diettracker.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sx1n.diettracker.R
import com.sx1n.diettracker.databinding.FragmentPhysicalActivityRegistrationBinding
import com.sx1n.diettracker.databinding.FragmentWeightGoalRegistrationBinding
import com.sx1n.diettracker.utils.viewBinding

class WeightGoalRegistrationFragment : Fragment(R.layout.fragment_weight_goal_registration) {

    private val binding by viewBinding(FragmentWeightGoalRegistrationBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}