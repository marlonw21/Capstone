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
import com.mwdevs.capstone.coins.data.remote.model.AvailableBooksResponse
import com.mwdevs.capstone.coins.data.remote.model.Payload
import com.mwdevs.capstone.coins.data.remote.model.ResponseModel
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
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvCoinsList.adapter = adapter
        val repo = APIServiceBuilder()
        val responseLiveData: LiveData<ResponseHandler<List<Payload>?>> = liveData{
            emit(repo.getTest())
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
//            if (it.isSuccessful){
//                val a = it.body()
//            }else{
//               val a = it.errorBody()
//            }
            adapter.submitList(it.data?.successBody)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}