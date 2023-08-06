package com.test.people.ui.popular

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.test.people.api.ApiResult
import com.test.people.di.App
import com.test.people.di.App.Companion.EMPTY_STRING
import com.test.people.model.LatestRate
import com.test.people.model.Valute
import com.test.people.ui.INavigateSort
import com.test.people.ui.R
import com.test.people.ui.SourceFragment
import com.test.people.ui.databinding.FragmentPopularBinding
import com.test.people.ui.favorites.FavoritesViewModel
import kotlinx.coroutines.launch

class PopularFragment : Fragment() {

    private var _binding: FragmentPopularBinding? = null

    private lateinit var popularViewModel: PopularViewModel
    private lateinit var favoritesViewModel: FavoritesViewModel

    private val binding
        get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _binding = FragmentPopularBinding.inflate(inflater, container, false)
        val root: View = binding.root

        activity?.let {
            popularViewModel = ViewModelProvider(this,
                (it.application as App).appComponent.getViewModelFactory()).get(PopularViewModel::class.java)

            initViewModelObservers()

            favoritesViewModel = ViewModelProvider(this,
                (it.application as App).appComponent.getViewModelFactory()).get(FavoritesViewModel::class.java)

            popularViewModel.loadListRates()
        }

        return root
    }

    private fun initViewModelObservers() {
        lifecycleScope.launch {
            popularViewModel.rates.collect { response ->
                with (binding) {
                    textError.isVisible = response is ApiResult.Error
                    recyclerView.isVisible = (response is ApiResult.Success
                                              && response.data != null
                                              && (response.data.rates?.size ?: 0) > 0)
                }

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
                    binding.buttonSort.visibility = View.GONE
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    fun showData(response: ApiResult<LatestRate>) {
        with(binding) {
            response.data?.let { data ->
                textTitle.text = "${getString(R.string.base_valute)} ${data.base ?: EMPTY_STRING}"
                data.rates?.let { rates ->
                    recyclerView.apply {
                        visibility = View.VISIBLE
                        adapter = RatesAdapter(rates, actChangedFavorite)
                    }
                }
                buttonSort.let {
                    it.visibility = View.VISIBLE
                    it.setOnClickListener(buttonSortClicked)
                }
            }
        }
    }

    private val buttonSortClicked = OnClickListener {
        (activity as INavigateSort).navigateSort(SourceFragment.sfPopular)
    }

    private val actChangedFavorite : (valute: Valute) -> Unit = { valute ->
        favoritesViewModel.changeFavorite(valute)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}