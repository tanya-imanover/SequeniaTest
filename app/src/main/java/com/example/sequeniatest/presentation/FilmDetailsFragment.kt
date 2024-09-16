package com.example.sequeniatest.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.sequeniatest.R
import com.example.sequeniatest.databinding.FragmentFilmDetailsBinding
import com.example.sequeniatest.domain.Film

class FilmDetailsFragment : Fragment() {

    private val binding: FragmentFilmDetailsBinding by lazy {
        FragmentFilmDetailsBinding.inflate(layoutInflater)
    }

    private val viewModel: FilmDetailsViewModel by lazy {
        ViewModelProvider(this)[FilmDetailsViewModel::class.java]
    }

    private var filmId: Int = ID_ERROR
    private lateinit var film: Film

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseParams()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        (activity as MainActivity).setBackButtonVisibility(true)
        (activity as MainActivity).setToolbarTitle(film.name?: EMPTY_STRING)

    }

    private fun parseParams() {
        arguments?.let {
            filmId = it.getInt(FILM_ID)
        }
        if (filmId == ID_ERROR) {
            Toast.makeText(
                this.context,
                getString(R.string.get_film_error),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun observeViewModel() {
        film = viewModel.getFilm(filmId)
        binding.progressBar.visibility = View.GONE
        binding.tvFilmNameTitle.text = film.localizedName
        binding.tvRating.text = viewModel.getRatingString(film.rating ?: 0.0)
        binding.tvGenreYear.text = viewModel.getYearGenresString(film)
        binding.textViewDescription.text = film.description

        Glide.with(binding.main)
            .load(film.imageUrl)
            .placeholder(R.drawable.placeholder)
            .into(binding.ivFilmPoster)
    }

    companion object {
        private const val FILM_ID = "film_id"
        private const val ID_ERROR = -1
        private const val EMPTY_STRING = ""

        @JvmStatic
        fun newInstance(filmId: Int) =
            FilmDetailsFragment().apply {
                arguments = Bundle().apply {
                    putInt(FILM_ID, filmId)
                }
            }
    }
}