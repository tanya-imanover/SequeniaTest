package com.example.sequeniatest.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.sequeniatest.R
import com.example.sequeniatest.databinding.FragmentFilmListBinding
import com.example.sequeniatest.presentation.adapter.FilmsAdapter
import com.example.sequeniatest.presentation.adapter.GenresAdapter


class FilmListFragment : Fragment() {

    private val binding: FragmentFilmListBinding by lazy {
        FragmentFilmListBinding.inflate(layoutInflater)
    }

    private val genresAdapter: GenresAdapter by lazy {
        GenresAdapter()
    }

    private val filmsAdapter: FilmsAdapter by lazy {
        FilmsAdapter()
    }

    private val viewModel: FilmListViewModel by lazy {
        ViewModelProvider(this)[FilmListViewModel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvFilmList.layoutManager = GridLayoutManager(this.context, 2)
        observeViewModel()
        setOnFilmClickListener()
        setOnGenreClickListener()
    }

    private fun observeViewModel() {
        viewModel.genres.observe(viewLifecycleOwner) {
            binding.rvGenreList.adapter = genresAdapter
            genresAdapter.submitList(it)
        }
        viewModel.filmList.observe(viewLifecycleOwner) {
            binding.rvFilmList.adapter = filmsAdapter
            filmsAdapter.submitList(it)
        }
        viewModel.isLoading.observe(viewLifecycleOwner) {
            if (it) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }
    }

    private fun setOnFilmClickListener() {
        filmsAdapter.onFilmItemClickListener = {
            it.id?.let {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.main_container, FilmDetailsFragment.newInstance(it))
                    .addToBackStack(null)
                    .commit()
            }
        }
    }

    private fun setOnGenreClickListener() {
        genresAdapter.onShopItemClickListener = {
            it.selected = !it.selected
            viewModel.genreSelected(it)
        }
    }

    companion object {
        fun newInstance() = FilmListFragment()
    }
}