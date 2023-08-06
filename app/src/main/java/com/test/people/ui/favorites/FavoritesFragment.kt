package com.test.people.ui.favorites

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.test.people.api.ApiResult
import com.test.people.di.App
import com.test.people.model.LatestRate
import com.test.people.model.Valute
import com.test.people.ui.INavigateSort
import com.test.people.ui.SourceFragment
import com.test.people.ui.databinding.FragmentFavoritesBinding

class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null

    private lateinit var favoritesViewModel: FavoritesViewModel

    private val binding
        get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        activity?.let {

            favoritesViewModel = ViewModelProvider(this,
                (it.application as App).appComponent.getViewModelFactory()).get(FavoritesViewModel::class.java)

            initViewModelObservers()

        }

        return root
    }

    private fun initViewModelObservers() {
        showData(null)
/*
        lifecycleScope.launch {
            popularViewModel.rates.collect { response ->
                if (response is ApiResult.Success && response.data != null) {
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
*/
    }

    @SuppressLint("SetTextI18n")
    fun showData(response: ApiResult<LatestRate>?) {
        with(binding) {
            buttonSort.setOnClickListener(buttonSortClicked)
/*
            response.data?.let { data ->
                textTitle.text = "${getString(com.test.people.ui.R.string.base_valute)} ${data.base ?: com.test.people.di.App.EMPTY_STRING}"
                data.rates?.let { rates ->
                    recyclerView.apply {
                        visibility = android.view.View.VISIBLE
                        adapter = RatesAdapter(rates, actChangedFavorite)
                    }
                }
                buttonSort.setOnClickListener(buttonSortClicked)
            }
*/
        }
    }

    private val buttonSortClicked = View.OnClickListener {
        (activity as INavigateSort).navigateSort(SourceFragment.sfFavorites)
    }

    private val actChangedFavorite : (valute: Valute) -> Unit = { valute ->
//        favoritesViewModel.changeFavorite(valute)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}