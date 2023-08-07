package com.test.people.ui.sort

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.test.people.api.ApiResult
import com.test.people.di.App
import com.test.people.di.App.Companion.PARAM_SOURCE_FRAGMENT
import com.test.people.model.Valute
import com.test.people.ui.R
import com.test.people.ui.SourceFragment
import com.test.people.ui.databinding.FragmentSortBinding
import kotlinx.coroutines.launch


class SortFragment : Fragment() {

    private var _binding: FragmentSortBinding? = null
    private val binding get() = _binding!!
    private lateinit var sortViewModel: SortViewModel
    private lateinit var sourceFragment: SourceFragment
    private var sortAdapter: RatesSortAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _binding = FragmentSortBinding.inflate(inflater, container, false)
        val root: View = binding.root

        sourceFragment = SourceFragment.valueOf(arguments?.getInt(PARAM_SOURCE_FRAGMENT))

        activity?.let {
            sortViewModel = ViewModelProvider(this,
                (it.application as App).appComponent.getViewModelFactory()).get(SortViewModel::class.java)
            sortViewModel.loadListRates(sourceFragment)

            initView()

            initViewModelObservers()
        }

        return root
    }

    private fun initViewModelObservers() {
        lifecycleScope.launch {
            sortViewModel.valutes.collect { response ->
                with (binding) {
                    textError.isVisible = response is ApiResult.Error
                    recyclerView.isVisible = (response is ApiResult.Success)
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
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    fun showData(response: ApiResult<List<Valute>>) {
        with(binding) {
            response.data?.let { data ->
                data.let { valutes ->
                    recyclerView.apply {
                        visibility = android.view.View.VISIBLE
                        sortAdapter = RatesSortAdapter(valutes, getTypeSort())
                        adapter = sortAdapter
                    }
                }
            }
        }
    }

    private fun getTypeSort(): RatesSortAdapter.TypeSort =
        RatesSortAdapter.TypeSort(TypeField.valueOf(binding.spinner.selectedItemPosition),
                              DirectionSort.valueOf(binding.direction.tag.toString().toInt()))

    private fun initView() {
        with(binding) {
            val adapter = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.sort_type,
                android.R.layout.simple_spinner_item
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.let {
                it.setAdapter(adapter)
                it.setOnItemSelectedListener(object : OnItemSelectedListener {
                    override fun onItemSelected(
                        parentView: AdapterView<*>,
                        selectedItemView: View,
                        position: Int,
                        id: Long
                    ) {
                        sortAdapter?.dataSort(getTypeSort())
                    }

                    override fun onNothingSelected(adapterView: AdapterView<*>?) {}
                })
            }

            direction.let {
                it.setOnClickListener {
                    val tag = it.tag.toString().toInt()
                    it as ImageButton
                    when (it.tag.toString().toInt()) {
                        0 -> {
                            it.tag = 1
                            it.setImageResource(R.drawable.ic_sort_down)
                        }
                        1 -> {
                            it.tag = 0
                            it.setImageResource(R.drawable.ic_sort_up)
                        }
                    }
                    sortAdapter?.dataSort(getTypeSort())
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}