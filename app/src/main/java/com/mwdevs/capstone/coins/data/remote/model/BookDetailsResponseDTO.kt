package com.mwdevs.capstone.coins.data.remote.model

import com.mwdevs.capstone.coins.domain.model.AskBidsModel
import com.mwdevs.capstone.coins.utils.toAsksToUIModel
import com.mwdevs.capstone.coins.utils.toBidsToUIModel

data class BookDetailsResponseDTO(
    val asks: List<AskDTO>,
    val bids: List<BidDTO>,
    val sequence: String,
    val updated_at: String
) {
    fun asksToUIModel(): List<AskBidsModel> = asks.map { it.toAsksToUIModel() }

    fun bidsToUIModel(): List<AskBidsModel> = bids.map { it.toBidsToUIModel() }
}
