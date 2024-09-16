package com.example.sequeniatest.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.sequeniatest.R
import com.example.sequeniatest.databinding.FragmentFilmListBinding
import com.example.sequeniatest.presentation.adapter.FilmsAdapter
import com.example.sequeniatest.presentation.adapter.GenresAdapter
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel


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

    private val viewModel: FilmListViewModel by viewModel<FilmListViewModel>()


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
        (activity as MainActivity).setToolbarTitle(getString(R.string.films_title))
        (activity as MainActivity).setBackButtonVisibility(false)
        setAdapters()
        setOnFilmClickListener()
        setOnGenreClickListener()
    }

    private fun observeViewModel() {
        viewModel.genres.observe(viewLifecycleOwner) {
            genresAdapter.submitList(it)
            binding.tvGenresTitle.visibility = View.VISIBLE
        }
        viewModel.filmList.observe(viewLifecycleOwner) {
            filmsAdapter.submitList(it)
            binding.tvFilmsTitle.visibility = View.VISIBLE
        }
        viewModel.isLoading.observe(viewLifecycleOwner) {
            setLoadingViewsVisibility(it)
        }
        viewModel.error.observe(viewLifecycleOwner) {
            if (it.isError) {
                showToast()
            }
        }
    }

    private fun setLoadingViewsVisibility(isLoading: Boolean) {
        if (isLoading) {
            binding.tvFilmsTitle.visibility = View.GONE
            binding.tvGenresTitle.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun setAdapters() {
        binding.rvGenreList.adapter = genresAdapter
        binding.rvFilmList.adapter = filmsAdapter
    }

    private fun showToast() {
        val snackBar =
            Snackbar.make(binding.root, R.string.string_network_error, Snackbar.LENGTH_LONG)
        snackBar.apply {
            setActionTextColor(ContextCompat.getColor(context, R.color.yellow))
            setBackgroundTint(ContextCompat.getColor(context, R.color.gray))
            setTextColor(ContextCompat.getColor(context, R.color.white))
            setAction(R.string.string_try_again) {
                viewModel.loadData()
            }
            show()
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