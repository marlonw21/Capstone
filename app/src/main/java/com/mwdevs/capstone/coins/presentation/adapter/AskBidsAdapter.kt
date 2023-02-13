package com.mwdevs.capstone.coins.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mwdevs.capstone.R
import com.mwdevs.capstone.coins.domain.model.AskBidsModel
import com.mwdevs.capstone.databinding.AsksBidsItemBinding

class AskBidsAdapter :
    ListAdapter<AskBidsModel, AskBidsAdapter.ViewHolder>(AskBidsDiffUtilCallback()) {

    inner class ViewHolder(private val binding: AsksBidsItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(model: AskBidsModel) = binding.apply {
            tvAmount.text = binding.root.context.getString(R.string.amount_text, model.amount)
            tvPrice.text = binding.root.context.getString(R.string.price_text, model.price)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(AsksBidsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) = with(getItem(position)) {
        holder.bind(this)
        Unit
    }
}

abstract class GenericDiffUtilCallback<T> : DiffUtil.ItemCallback<T>()

class AskBidsDiffUtilCallback: GenericDiffUtilCallback<AskBidsModel>() {
    override fun areItemsTheSame(oldItem: AskBidsModel, newItem: AskBidsModel): Boolean =
        oldItem.amount == newItem.amount && oldItem.price == oldItem.price

    override fun areContentsTheSame(oldItem: AskBidsModel, newItem: AskBidsModel): Boolean =
       oldItem == newItem

}