package com.mwdevs.capstone.coins.domain.model

data class CoinUIModel(
    val id: String,
    val coinResource: Map<Int, List<Int>>,
    val extras: String = ""
)
