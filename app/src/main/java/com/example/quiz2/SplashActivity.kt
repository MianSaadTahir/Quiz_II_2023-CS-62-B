package com.example.quiz2

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.quiz2.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Delay for 2 seconds then navigate to ComplaintListActivity
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, ComplaintListActivity::class.java))
            finish()
        }, 2000)
    }
}