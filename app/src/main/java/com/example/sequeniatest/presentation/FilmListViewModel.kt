package com.example.sequeniatest.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sequeniatest.data.repository.FilmRepositoryImpl
import com.example.sequeniatest.domain.Genre
import com.example.sequeniatest.domain.GenreDeselectedUseCase
import com.example.sequeniatest.domain.GenreSelectedUseCase
import com.example.sequeniatest.domain.GetFilmListUseCase
import com.example.sequeniatest.domain.GetGenresUseCase
import com.example.sequeniatest.domain.LoadDataUseCase
import kotlinx.coroutines.launch

class FilmListViewModel : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean>
        get() = _isLoading

//    private val _genreSelected = MutableLiveData<Genre>()
//    val genreSelected: LiveData<Genre>
//        get() = _genreSelected

    private val repository = FilmRepositoryImpl
    private val getFilmListUseCase = GetFilmListUseCase(repository)
    private val getGenresUseCase = GetGenresUseCase(repository)
    private val genreSelectedUseCase = GenreSelectedUseCase(repository)
    private val genreDeselectedUseCase = GenreDeselectedUseCase(repository)
    private val loadDataUseCase = LoadDataUseCase(repository)

    val filmList = getFilmListUseCase()
    val genres = getGenresUseCase()

    init{
        viewModelScope.launch {
            loadDataUseCase()
        }
    }

    fun genreSelected(genre: Genre){
        if(genre.selected) {
            genreSelectedUseCase(genre)
        } else {
            genreDeselectedUseCase()
        }
    }




}