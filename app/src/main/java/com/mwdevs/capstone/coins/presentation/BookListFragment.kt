package com.mwdevs.capstone.coins.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.mwdevs.capstone.R
import com.mwdevs.capstone.coins.presentation.adapter.BookListAdapter
import com.mwdevs.capstone.coins.presentation.viewModel.BookListViewModel
import com.mwdevs.capstone.databinding.FragmentFirstBinding
import com.mwdevs.capstone.utils.models.ResponseHandler
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */

@AndroidEntryPoint
class BookListFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var  adapter: BookListAdapter
    private val vModel: BookListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        adapter = BookListAdapter {
            Log.e("click", it)
            findNavController()
                .navigate(R.id.action_FirstFragment_to_SecondFragment, Bundle().apply {
                    this.putString("book", it)
                })
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvCoinsList.adapter = adapter
        vModel.getBooks()
        vModel.bookList.observe(viewLifecycleOwner){ response ->
            when(response){
                is ResponseHandler.Success -> adapter.submitList(response.data)
                is ResponseHandler.Error -> {
                    response.data?.let {
                        adapter.submitList(it)
                    }
                    Log.e("Error", response.errorMsg ?: "-")
                }
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}