package com.example.roomapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.roomapp.data.MovieDatabase
import com.example.roomapp.repository.MovieRepository
import com.example.roomapp.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Movie>>

    private val repository: MovieRepository

    init {
        val userDao = MovieDatabase.getDatabase(
            application
        ).userDao()
        repository = MovieRepository(userDao)
        readAllData = repository.readAllData

    }

    fun addMovie(movie: Movie){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addMovie(movie)
        }
    }

    fun updateMovie(movie: Movie){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateMovie(movie)
        }
    }

    fun deleteMovie(movie: Movie){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteMovie(movie)
        }
    }

    fun deleteAllMovies(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllMovies()
        }
    }

    fun search(name : String) : LiveData<List<Movie>>{
       return repository.search(name)
    }
}