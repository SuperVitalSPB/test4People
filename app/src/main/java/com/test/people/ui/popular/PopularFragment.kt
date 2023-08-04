package com.test.people.ui.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.test.people.api.ApiResult
import com.test.people.di.App
import com.test.people.ui.R
import com.test.people.ui.databinding.FragmentPopularBinding

class PopularFragment : Fragment() {

    private var _binding: FragmentPopularBinding? = null

    private val binding
        get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val popularViewModel: PopularViewModel by viewModels {
            PopularViewModelFactory((activity?.application as App).retrofit)
        }

        _binding = FragmentPopularBinding.inflate(inflater, container, false)
        val root: View = binding.root


        popularViewModel.rates.observe(viewLifecycleOwner) {response ->
            if (response is ApiResult.Success && response.data != null) {
                binding.textTitle.apply {
                    text = "${getString(R.string.base_valute)} ${response.data.base}"
                }
                // trainingAdapter.setData(sortLessons(it.data))

            } else {
                binding.textError.apply {
                    visibility = View.VISIBLE
                    text = response.errorMessage
                    Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
                }

            }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}