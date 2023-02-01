package com.mwdevs.capstone

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.navigation.fragment.findNavController
import com.mwdevs.capstone.coins.data.remote.model.Payload
import com.mwdevs.capstone.coins.data.remote.model.ResponseModel
import com.mwdevs.capstone.coins.domain.model.CoinUIModel
import com.mwdevs.capstone.coins.domain.use_case.GetBookListUseCase
import com.mwdevs.capstone.coins.presentation.adapter.BookListAdapter
import com.mwdevs.capstone.databinding.FragmentFirstBinding
import com.mwdevs.capstone.utils.APIServiceBuilder
import com.mwdevs.capstone.utils.retrofit.models.ResponseHandler

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var  adapter: BookListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

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
        val repo = GetBookListUseCase()
        val responseLiveData: LiveData<ResponseHandler<List<CoinUIModel>?>> = liveData{
            emit(repo.invoke())
        }
        responseLiveData.observe(viewLifecycleOwner){
            Log.e("response", it?.toString() ?: "error")
            when (it){
                is ResponseHandler.Success ->{
                    Log.e("successs", it.data?.successBody.toString())
                }
                is ResponseHandler.Error ->{

                }
            }
            adapter.submitList(it.data?.successBody)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}