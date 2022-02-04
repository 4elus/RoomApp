package com.example.roomapp.repository

import androidx.lifecycle.LiveData
import com.example.roomapp.data.MovieDao
import com.example.roomapp.model.Movie

class MovieRepository(private val movieDao: MovieDao) {

    val readAllData: LiveData<List<Movie>> = movieDao.readAllData()

    suspend fun addMovie(movie: Movie){
        movieDao.addMovie(movie)
    }

    suspend fun updateMovie(movie: Movie){
        movieDao.updateMovie(movie)
    }

    suspend fun deleteMovie(movie: Movie){
        movieDao.deleteMovie(movie)
    }

    suspend fun deleteAllMovies(){
        movieDao.deleteAllMovies()
    }

    fun search(name : String): LiveData<List<Movie>>{
        return movieDao.search(name)
    }

}