package com.example.sequeniatest.presentation

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sequeniatest.R
import com.example.sequeniatest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding : ActivityMainBinding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setSupportActionBar(binding.tbMain)
        setBackButtonVisibility(false)
        binding.btnBack.setOnClickListener {
            supportFragmentManager.popBackStack()

        }
    }

    fun setToolbarTitle(title: String){
        binding.tvToolbarTitle.text = title
    }

    fun setBackButtonVisibility(visible: Boolean) {
        with(binding) {
        when(visible){
            true -> btnBack.visibility = View.VISIBLE
            false -> btnBack.visibility = View.GONE
        }
    }

    }



}