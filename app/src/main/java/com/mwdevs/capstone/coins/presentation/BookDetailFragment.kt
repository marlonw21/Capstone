package com.mwdevs.capstone.coins.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.mwdevs.capstone.R
import com.mwdevs.capstone.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class BookDetailFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
//        val repo = GetBookDetailUseCase()
//        val repo2 = APIServiceBuilder()
//        val responseLiveData: LiveData<ResponseHandler<BookDetailsResponse?>> = liveData{
//            emit(repo.invoke(arguments?.getString("book") ?: ""))
//        }
//        val responseTickerLiveData: LiveData<ResponseHandler<TickerResponse?>> = liveData{
//            emit(repo2.getTest3(arguments?.getString("book") ?: ""))
//        }
//        responseTickerLiveData.observe(viewLifecycleOwner){
//            when(it){
//                is ResponseHandler.Success ->{
//                    Log.e("ticker", it.data?.successBody.toString())
//                }
//                is ResponseHandler.Error ->{
//                    Log.e("erreo", it?.data?.errorBody.toString())
//                }
//            }
//        }
//
//
//        responseLiveData.observe(viewLifecycleOwner){
//            when(it){
//                is ResponseHandler.Success ->{
//                    Log.e("bookdetail", it.data?.successBody.toString())
//                }
//                is ResponseHandler.Error ->{
//                    Log.e("erreo", it?.data?.errorBody.toString())
//                }
//            }
//        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}