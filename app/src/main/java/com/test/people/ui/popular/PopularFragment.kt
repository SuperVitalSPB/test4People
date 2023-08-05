package com.test.people.ui.popular

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.test.people.api.ApiResult
import com.test.people.di.App
import com.test.people.model.LatestRate
import com.test.people.model.LatestRateEntity
import com.test.people.ui.R
import com.test.people.ui.databinding.FragmentPopularBinding

class PopularFragment : Fragment() {

    private var _binding: FragmentPopularBinding? = null

    lateinit var popularViewModel: PopularViewModel

    private val binding
        get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _binding = FragmentPopularBinding.inflate(inflater, container, false)
        val root: View = binding.root

        activity?.let {
            popularViewModel = ViewModelProvider(this,
                (it.application as App).appComponent.getViewModelFactory()).get(PopularViewModel::class.java)

            initViewModelObservers()
        }
        return root
    }

    fun initViewModelObservers() {
        popularViewModel.rates.observe(viewLifecycleOwner) {response ->
            if (response is ApiResult.Success && response.data != null) {
                showData(response)
            } else {
                binding.textError.apply {
                    visibility = View.VISIBLE
                    text = response.errorMessage
                    Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    fun showData(response: ApiResult<LatestRate>) {
        with(binding) {
            textTitle.apply {
                text = "${getString(R.string.base_valute)} ${response.data?.base ?: ""}"
            }
            recyclerView.apply {
                visibility =View.VISIBLE
                adapter = RatesAdapter(response.data!!.rates)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}