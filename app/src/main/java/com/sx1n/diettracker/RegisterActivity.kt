package com.sx1n.diettracker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sx1n.diettracker.databinding.ActivityRegisterBinding
import com.sx1n.diettracker.listeners.OnStepChangedListener

class RegisterActivity : AppCompatActivity(), OnStepChangedListener {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }

    override fun onStepChanged(step: Int) {
        val totalSteps = 8
        val progress = ((step.toFloat() / totalSteps) * 100).toInt()
        binding.registerProgressBar.progress = progress
    }
}