package com.example.timeline.ui

import android.content.Intent
import android.os.Bundle
import com.example.timeline.databinding.ActivitySplashBinding
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : DaggerAppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val nextIntent = Intent(this, RootActivity::class.java)
        CoroutineScope(Dispatchers.Main).launch {
            delay(2000L)
            startActivity(nextIntent)
            finish()
        }
    }
}
