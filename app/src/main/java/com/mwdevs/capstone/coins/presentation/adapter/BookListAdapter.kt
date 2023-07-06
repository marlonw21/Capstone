package com.mwdevs.capstone.coins.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mwdevs.capstone.R
import com.mwdevs.capstone.coins.domain.model.CoinUIModel
import com.mwdevs.capstone.databinding.BookItemBinding
import com.mwdevs.capstone.utils.animation.clickListenerWithAnimation

class BookListAdapter(
    private val onClick: (String) -> Unit
) : ListAdapter<CoinUIModel, BookListAdapter.ViewHolder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(BookItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = with(getItem(position)) {
        holder.bind(this)
        Unit
    }

    inner class ViewHolder(private val binding: BookItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(model: CoinUIModel) = binding.apply {
            tvName.setText(model.coinResource.firstNotNullOf { it.key })
            ivLogoMajor.setImageDrawable(AppCompatResources.getDrawable(binding.root.context, model.coinResource.firstNotNullOf { it.value[0] }))
            ivLogoMinor.setImageDrawable(AppCompatResources.getDrawable(binding.root.context, model.coinResource.firstNotNullOf { it.value[1] }))
            cardConstraintLayout.clickListenerWithAnimation(
                defaultColor = R.color.waikawa_gray,
                onClickColor = R.color.mirage
            ) {
                onClick(model.id)
            }
        }
    }
}

class DiffUtilCallback : DiffUtil.ItemCallback<CoinUIModel>() {
    override fun areItemsTheSame(oldItem: CoinUIModel, newItem: CoinUIModel): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: CoinUIModel, newItem: CoinUIModel): Boolean =
        oldItem == newItem
}
