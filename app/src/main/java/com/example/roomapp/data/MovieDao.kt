package com.example.roomapp.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.roomapp.model.Movie

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMovie(movie: Movie)

    @Update
    suspend fun updateMovie(movie: Movie)

    @Delete
    suspend fun deleteMovie(movie: Movie)

    @Query("DELETE FROM movie_table")
    suspend fun deleteAllMovies()

    @Query("SELECT * FROM movie_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Movie>>

    @Query("SELECT * FROM movie_table WHERE name LIKE :search")
    fun search(search: String?): LiveData<List<Movie>>
}