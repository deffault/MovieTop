package com.example.movietop.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movietop.R
import com.example.movietop.databinding.ActivityMainBinding
import com.example.movietop.ui.movies.list.MoviesListFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(binding.fragmentHost.id, MoviesListFragment())
                .commit()
        }
    }
}