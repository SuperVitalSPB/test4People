package com.test.people.ui.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.test.people.ui.databinding.FragmentPopularBinding

class PopularFragment : Fragment() {

    private var _binding: FragmentPopularBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val popularViewModel = ViewModelProvider(this).get(PopularViewModel::class.java)

        _binding = FragmentPopularBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textPopular
        popularViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}