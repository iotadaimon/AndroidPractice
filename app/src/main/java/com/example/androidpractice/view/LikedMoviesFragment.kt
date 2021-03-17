package com.example.androidpractice.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.androidpractice.MovieView
import com.example.androidpractice.R
import com.example.androidpractice.model.entity.Movie


// TODO
class LikedMoviesFragment : Fragment(), MovieView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_liked_movies, container, false)

//        val movieList = arguments?.getParcelableArrayList<Movie>(MainActivity.BUNDLE_DATA_KEY)

        val recyclerView: RecyclerView = view.findViewById(R.id.liked_movies_recycler_view)
//        recyclerView.adapter = MovieAdapter(movieList ?: emptyList())

        return view
    }

    override fun showMovies(movies: List<Movie>) {
        TODO("Not yet implemented")
    }

    override fun showMovieDetails(movie: Movie) {
        TODO("Not yet implemented")
    }

    override fun showErrorToast() {
        super.showErrorToast()
    }

}