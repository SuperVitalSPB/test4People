package com.test.people.ui.sort

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.test.people.ui.databinding.FragmentSortBinding

class SortFragment : Fragment() {

    private var _binding: FragmentSortBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val sortViewModel = ViewModelProvider(this).get(SortViewModel::class.java)

        _binding = FragmentSortBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textSort
        sortViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}