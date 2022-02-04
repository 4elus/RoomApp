package com.example.roomapp.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomapp.R
import com.example.roomapp.model.Movie
import com.example.roomapp.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class AddFragment : Fragment() {

    private lateinit var mMovieViewModel: MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        mMovieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)

        view.add_btn.setOnClickListener {
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase() {
        val name = addName_et.text.toString()
        val date = addDate_et.text.toString()
        val budget = addBudget_et.text
        val isViewed = add_is_viewed.isChecked

        if(inputCheck(name, date, budget)){
            // Create User Object
            val movie = Movie(
                0,
                name,
                date,
                Integer.parseInt(budget.toString()),
                isViewed
            )
            // Add Data to Database
            mMovieViewModel.addMovie(movie)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
            // Navigate Back
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(name: String, date: String, budget: Editable): Boolean{
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(date) && budget.isEmpty())
    }

}