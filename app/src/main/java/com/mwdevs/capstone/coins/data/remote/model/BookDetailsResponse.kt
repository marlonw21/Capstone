package com.mwdevs.capstone.coins.data.remote.model

import com.mwdevs.capstone.coins.domain.model.AskBidsModel

data class BookDetailsResponse(
    val asks: List<Ask>,
    val bids: List<Bid>,
    val sequence: String,
    val updated_at: String
){
    fun asksToUIModel(): List<AskBidsModel> {
        val asks = mutableListOf<AskBidsModel>()
        this.asks.forEach { ask ->
            asks.add(
                AskBidsModel(
                    amount = ask.amount,
                    price = ask.price
                )
            )
        }
        return asks.toList()
    }

    fun bidsToUIModel(): List<AskBidsModel> {
        val newBids = mutableListOf<AskBidsModel>()
        bids.forEach { ask ->
            newBids.add(
                AskBidsModel(
                    amount = ask.amount,
                    price = ask.price
                )
            )
        }
        return newBids.toList()
    }
}