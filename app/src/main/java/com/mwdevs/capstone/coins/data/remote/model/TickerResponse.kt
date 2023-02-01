package com.mwdevs.capstone.coins.data.remote.model

data class TickerResponse(
    val ask: String,
    val bid: String,
    val book: String,
    val created_at: String,
    val high: String,
    val last: String,
    val low: String,
    val volume: String,
    val vwap: String
)