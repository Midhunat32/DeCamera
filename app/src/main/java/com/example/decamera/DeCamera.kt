package com.example.decamera

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.example.myapplication.databinding.ActivityMainBinding

class DeCamera : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

}