package com.sx1n.diettracker.ui.activities

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import com.sx1n.diettracker.databinding.ActivityRegisterBinding
import com.sx1n.diettracker.ui.listeners.OnStepChangedListener

class RegisterActivity : AppCompatActivity(), OnStepChangedListener {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)

        binding.registerBackButton.setOnClickListener {
            finish()
        }

        setContentView(binding.root)
    }

    override fun onStepChanged(step: Int) {
        val totalSteps = 8
        val progress = ((step.toFloat() / totalSteps) * 800).toInt()

        val animator = ObjectAnimator.ofInt(binding.registerProgressBar, "progress", progress)
        animator.duration = 500
        animator.interpolator = AccelerateDecelerateInterpolator()

        animator.start()
    }
}