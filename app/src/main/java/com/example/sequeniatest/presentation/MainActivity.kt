package com.example.sequeniatest.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.sequeniatest.R
import com.example.sequeniatest.data.repository.FilmRepositoryImpl
import com.example.sequeniatest.domain.FilmRepository
import com.example.sequeniatest.domain.GetFilmListUseCase
import com.example.sequeniatest.domain.GetGenresCase
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val repository by lazy {
        FilmRepositoryImpl()
    }
    private val getFilmListUseCase by lazy {
        GetFilmListUseCase(repository)
    }
    private val getGenresCase by lazy {
        GetGenresCase(repository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        lifecycleScope.launch {
            repository.loadFilms()
            var films = getFilmListUseCase()
            val genr = getGenresCase()

            Log.d("MainActivity films", films.value.toString())
            Log.d("MainActivity genres", genr.value.toString())

        }

    }
}