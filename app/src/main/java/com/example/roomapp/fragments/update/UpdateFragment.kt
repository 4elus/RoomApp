package com.example.roomapp.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.roomapp.R
import com.example.roomapp.model.Movie
import com.example.roomapp.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mMovieViewModel: MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mMovieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)

        view.updateName_et.setText(args.currentMovie.name)
        view.updateDate_et.setText(args.currentMovie.date)
        view.updateBudget_et.setText(args.currentMovie.budget.toString())
        view.is_viewed.isChecked = args.currentMovie.isViewed

        view.update_btn.setOnClickListener {
            updateItem()
        }

        // Add menu
        setHasOptionsMenu(true)

        return view
    }

    private fun updateItem() {
        val name =updateName_et.text.toString()
        val date = updateDate_et.text.toString()
        val budget = Integer.parseInt(updateBudget_et.text.toString())
        val isViewed = is_viewed.isChecked

        if (inputCheck(name, date, updateBudget_et.text)) {
            // Create User Object
            val updatedMovie = Movie(args.currentMovie.id, name, date, budget, isViewed)
            // Update Current User
            mMovieViewModel.updateMovie(updatedMovie)
            Toast.makeText(requireContext(), "Updated Successfully!", Toast.LENGTH_SHORT).show()
            // Navigate Back
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mMovieViewModel.deleteMovie(args.currentMovie)
            Toast.makeText(
                requireContext(),
                "Successfully removed: ${args.currentMovie.name}",
                Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete ${args.currentMovie.name}?")
        builder.setMessage("Are you sure you want to delete ${args.currentMovie.name}?")
        builder.create().show()
    }
}
