package com.test.people.ui.favorites

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.test.people.api.ApiResult
import com.test.people.db.Favorites
import com.test.people.di.App
import com.test.people.model.Valute
import com.test.people.ui.INavigateSort
import com.test.people.ui.R
import com.test.people.ui.SourceFragment
import com.test.people.ui.databinding.FragmentFavoritesBinding
import com.test.people.ui.popular.PopularViewModel
import kotlinx.coroutines.launch

class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null

    private lateinit var favoritesViewModel: FavoritesViewModel
    private lateinit var popularViewModel: PopularViewModel

    private val binding
        get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        activity?.let {

            popularViewModel = ViewModelProvider(this,
                (it.application as App).appComponent.getViewModelFactory()).get(PopularViewModel::class.java)

            favoritesViewModel = ViewModelProvider(this,
                (it.application as App).appComponent.getViewModelFactory()).get(FavoritesViewModel::class.java)
            favoritesViewModel.noDataString = getString(R.string.no_favorites)
            favoritesViewModel.loadFavorites()
            initViewModelObservers()

        }

        return root
    }

    private fun initViewModelObservers() {
        lifecycleScope.launch {
            favoritesViewModel.favRates.collect { response ->
                with (binding) {
                    textError.isVisible = response is ApiResult.Error
                    recyclerView.isVisible = (response is ApiResult.Success)
                    titleLayout.isVisible = recyclerView.isVisible
                }
                if (response is ApiResult.Success) {
                    showData(response)
                } else {
                    binding.textError.apply {
                        response.errorMessage?.let {
                            visibility = View.VISIBLE
                            text = it
                            Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    fun showData(response: ApiResult<List<Favorites>>) {
        with(binding) {
            response.data?.let { data ->
                textTitle.text = "${getString(R.string.base_valute)} " +
                        (popularViewModel.rates.value.data?.base ?: App.EMPTY_STRING)
                data.let { favorites ->
                    recyclerView.apply {
                        visibility = android.view.View.VISIBLE
                        adapter = FavoritesAdapter(favorites, actChangedFavorite)
                    }
                }
                buttonSort.let {
                    it.visibility = View.VISIBLE
                    it.setOnClickListener(buttonSortClicked)
                }
            }
        }
    }

    private val buttonSortClicked = View.OnClickListener {
        (activity as INavigateSort).navigateSort(SourceFragment.sfFavorites)
    }

    private val actChangedFavorite : (favorites: Favorites) -> Unit = { favorites ->
        favorites.name.let { favoritesViewModel.changeFavorite(Valute(it, 0.0, false)) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}