package com.sx1n.diettracker.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sx1n.diettracker.R
import com.sx1n.diettracker.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)

        val animationResId = when ((1..3).random()) {
            1 -> R.raw.animation1
            2 -> R.raw.animation2
            else -> R.raw.animation3
        }

        binding.lavWelcome.setAnimation(animationResId)

        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        binding.btnLogin.setOnClickListener {

        }

        setContentView(binding.root)
    }

//    override fun onConfigurationChanged(newConfig: Configuration) {
//        super.onConfigurationChanged(newConfig)
//        recreate()
//    }
}