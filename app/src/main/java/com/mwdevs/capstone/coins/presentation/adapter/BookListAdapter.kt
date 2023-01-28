package com.mwdevs.capstone.coins.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mwdevs.capstone.R
import com.mwdevs.capstone.coins.data.remote.model.Payload
import com.mwdevs.capstone.databinding.BookItemBinding
import com.mwdevs.capstone.utils.animation.clickListenerWithAnimation

class BookListAdapter(
    private val onClick: (String) -> Unit
) : ListAdapter<Payload, BookListAdapter.ViewHolder>(DiffUtilCallback()) {
    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(BookItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = with(getItem(position)) {
        holder.bind(this)
        Unit
    }

    inner class ViewHolder(private val binding: BookItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(model: Payload) = binding.apply {
            bookModel = model
            cardConstraintLayout.clickListenerWithAnimation(
                defaultColor = R.color.waikawa_gray,
                onClickColor = R.color.mirage
            ){
                onClick(model.book)
            }
        }
    }
}

class DiffUtilCallback : DiffUtil.ItemCallback<Payload>() {
    override fun areItemsTheSame(oldItem: Payload, newItem: Payload): Boolean =
        oldItem.book == newItem.book

    override fun areContentsTheSame(oldItem: Payload, newItem: Payload): Boolean =
        oldItem == newItem
}


