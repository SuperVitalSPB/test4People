package com.test.people.ui.sort

import com.test.people.ui.R
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.test.people.di.App.Companion.PARAM_SOURCE_FRAGMENT
import com.test.people.ui.SourceFragment
import com.test.people.ui.databinding.FragmentSortBinding


class SortFragment : Fragment() {

    private var _binding: FragmentSortBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val sortViewModel = ViewModelProvider(this).get(SortViewModel::class.java)

        _binding = FragmentSortBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val sourceFragment = SourceFragment.valueOf(arguments?.getInt(PARAM_SOURCE_FRAGMENT))

        Log.d("SortFragment", "SourceFragment: ${sourceFragment.toString()}")
        initView()


        return root
    }

    fun initView() {
        with(binding) {
            val adapter = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.sort_type,
                android.R.layout.simple_spinner_item
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.setAdapter(adapter)

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
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}