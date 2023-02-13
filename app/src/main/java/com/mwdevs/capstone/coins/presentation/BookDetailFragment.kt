package com.mwdevs.capstone.coins.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.mwdevs.capstone.R
import com.mwdevs.capstone.coins.presentation.adapter.AskBidsAdapter
import com.mwdevs.capstone.coins.presentation.viewModel.BookDetailViewModel
import com.mwdevs.capstone.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class BookDetailFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val vModel: BookDetailViewModel by activityViewModels()
    private lateinit var asksAdapter: AskBidsAdapter
    private lateinit var bidsAdapter: AskBidsAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        asksAdapter = AskBidsAdapter()
        bidsAdapter = AskBidsAdapter()
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapters()
        callApi()
        initObservers()
    }

    private fun setAdapters() = binding.apply {
        rvAsksList.adapter = asksAdapter
        rvBidsList.adapter = bidsAdapter
    }

    private fun callApi(){
        vModel.getBookDetail(arguments?.getString("book") ?: "")
        vModel.getTicker(arguments?.getString("book") ?: "")

    }

    private fun initObservers(){
        vModel.bidsModel.observe(viewLifecycleOwner){
            bidsAdapter.submitList(it)
        }
        vModel.askModel.observe(viewLifecycleOwner){
            asksAdapter.submitList(it)
        }
        vModel.tickerModel.observe(viewLifecycleOwner){
            val responseData = it?.data?.successBody
            binding.apply {
                tvHighPrice.text = root.context.getString(R.string.highest_price_text, responseData?.high)
                tvLastPrice.text = root.context.getString(R.string.last_price_text, responseData?.last)
                tvLowestPrice.text = root.context.getString(R.string.lowest_price_text, responseData?.low)
            }
        }
        vModel.errorHandler.observe(viewLifecycleOwner){
            it?.let {
                Toast.makeText(requireContext(), binding.root.context.getString(R.string.general_error), Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}