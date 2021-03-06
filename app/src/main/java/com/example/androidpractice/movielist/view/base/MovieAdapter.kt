package com.example.androidpractice.movielist.view.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.androidpractice.MovieListContract
import com.example.androidpractice.R
import com.example.androidpractice.databinding.RecyclerviewItemMovieBinding
import com.example.androidpractice.model.entity.Movie

class MovieAdapter(
    private var movieList: MutableList<Movie>,
    private val movieListView: MovieListContract.View
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = RecyclerviewItemMovieBinding.bind(itemView)

        fun bind(movie: Movie) {
            binding.movieItemTitleTextView.text = movie.title
            binding.movieItemVoteAverageTextView.text = movie.voteAverage.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_item_movie, parent, false)

        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieList[position])
        holder.itemView.setOnClickListener {
            movieListView.showMovieDetails(movieList[position]) // Open movie details on click on the movie list item
        }
    }

    internal fun getData(): List<Movie> = movieList

    internal fun setData(newMovieList: List<Movie>) {
        val diffResult: DiffUtil.DiffResult =
            DiffUtil.calculateDiff(DiffCallback(getData(), newMovieList))

        movieList.clear()
        movieList.addAll(newMovieList)

        diffResult.dispatchUpdatesTo(this)
    }
}
