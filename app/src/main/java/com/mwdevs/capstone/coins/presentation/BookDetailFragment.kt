package com.mwdevs.capstone.coins.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.snackbar.Snackbar
import com.mwdevs.capstone.coins.presentation.adapter.AskBidsAdapter
import com.mwdevs.capstone.coins.presentation.viewModel.BookDetailViewModel
import com.mwdevs.capstone.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class BookDetailFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val vModel: BookDetailViewModel by activityViewModels()
    private lateinit var asksAdaper: AskBidsAdapter
    private lateinit var bidsAdaper: AskBidsAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        asksAdaper = AskBidsAdapter()
        bidsAdaper = AskBidsAdapter()
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapters()
        callApi()
        initObservers()
    }

    private fun setAdapters() = binding.apply {
        rvAsksList.adapter = asksAdaper
        rvBidsList.adapter = bidsAdaper
    }

    private fun callApi(){
        vModel.getBookDetail(arguments?.getString("book") ?: "")
        vModel.getTicker(arguments?.getString("book") ?: "")

    }

    private fun initObservers(){
        vModel.bidsModel.observe(viewLifecycleOwner){
            bidsAdaper.submitList(it)
        }
        vModel.askModel.observe(viewLifecycleOwner){
            asksAdaper.submitList(it)
        }
        vModel.tickerModel.observe(viewLifecycleOwner){
            val responseData = it?.data?.successBody
            binding.apply {
                tvHighPrice.text = "Highest Price: ${responseData?.high}"
                tvLastPrice.text = "Last Price: ${responseData?.last}"  //TODO utilizar place string holders
                tvLowestPrice.text = "Lowest Price: ${responseData?.low}"
            }
        }
        vModel.errorHandler.observe(viewLifecycleOwner){
            it?.let {
                Toast.makeText(requireContext(), "An Error Ocurred", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}