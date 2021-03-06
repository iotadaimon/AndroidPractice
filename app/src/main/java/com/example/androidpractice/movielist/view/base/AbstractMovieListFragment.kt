package com.example.androidpractice.movielist.view.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidpractice.MovieListContract
import com.example.androidpractice.R
import com.example.androidpractice.databinding.FragmentMovieListBinding
import com.example.androidpractice.model.entity.Movie
import com.example.androidpractice.moviedetails.MovieDetailsActivity
import com.google.android.material.progressindicator.LinearProgressIndicator
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class AbstractMovieListFragment : DaggerFragment(),
    MovieListContract.View {

    open lateinit var presenter: MovieListContract.Presenter

    @JvmField
    @Inject
    var pageSize: Int = 0

    private lateinit var binding: FragmentMovieListBinding

    private lateinit var progressIndicator: LinearProgressIndicator

    private lateinit var recyclerViewAdapter: MovieAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        presenter.attachView(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieListBinding.inflate(inflater, container, false)
        val view = binding.root

        activity?.let {
            progressIndicator = it.findViewById(R.id.main_activity_progress_indicator)
        }

        binding.movieListRecyclerView.adapter = MovieAdapter(mutableListOf(), this)
        recyclerViewAdapter = binding.movieListRecyclerView.adapter as MovieAdapter

        binding.movieListRecyclerView.addOnScrollListener(
            MovieListScrollListener(
                pageSize,
                binding.movieListRecyclerView.layoutManager as LinearLayoutManager,
                presenter
            )
        )

        return view
    }

    override fun showMovies(movies: List<Movie>) {
        recyclerViewAdapter.setData(movies)
    }

    override fun showMovieDetails(movie: Movie) {
        val intent = Intent(context, MovieDetailsActivity::class.java)
        intent.putExtra(MovieDetailsActivity.DATA_MOVIE, movie)
        startActivity(intent)
    }

    override fun showProgressIndicator() = progressIndicator.show()

    override fun hideProgressIndicator() = progressIndicator.hide()

    protected fun showToast(message: String) {
        Toast.makeText(
            context,
            message,
            Toast.LENGTH_SHORT
        ).show()
    }
}
