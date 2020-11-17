package com.example.timeline.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.plusAssign
import com.example.timeline.R
import com.example.timeline.databinding.ActivityRootBinding
import dagger.android.support.DaggerAppCompatActivity

class RootActivity : DaggerAppCompatActivity() {

    private lateinit var binding: ActivityRootBinding

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_root)
    }
}
