package com.mwdevs.capstone.coins.data.remote.model

data class BookDetailsResponse(
    val asks: List<Ask>,
    val bids: List<Bid>,
    val sequence: String,
    val updated_at: String
)