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
import com.example.sequeniatest.domain.LoadError
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class FilmListViewModel : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _error = MutableLiveData<LoadError>()
    val error: LiveData<LoadError>
        get() = _error

    private val repository = FilmRepositoryImpl
    private val getFilmListUseCase = GetFilmListUseCase(repository)
    private val getGenresUseCase = GetGenresUseCase(repository)
    private val genreSelectedUseCase = GenreSelectedUseCase(repository)
    private val genreDeselectedUseCase = GenreDeselectedUseCase(repository)
    private val loadDataUseCase = LoadDataUseCase(repository)

    val filmList = getFilmListUseCase()
    val genres = getGenresUseCase()


    init {
        loadData()
    }

    fun genreSelected(genre: Genre) {
        if (genre.selected) {
            genreSelectedUseCase(genre)
        } else {
            genreDeselectedUseCase()
        }
    }

    fun loadData(){
        val handler = CoroutineExceptionHandler { _, throwable -> _error.value = LoadError(true, "Error ${throwable.message}") }
        viewModelScope.launch(handler) {
            _isLoading.value = true
            _error.value = LoadError(false, "")
            try {
                loadDataUseCase()
            } catch (e: Exception){
                _error.value = LoadError(true, e.message?:"Unknown error")
            }
        }.invokeOnCompletion {
            _isLoading.value = false
        }
    }


}