package com.mwdevs.capstone.utils

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.mwdevs.capstone.R

fun <T>String.fromJsonToClass(toClass: Class<T>): T? = try {
    Gson().fromJson(this, toClass)
}catch (e: Exception){
    when(e){
        is JsonSyntaxException -> null
        else -> null
    }
}

fun getDrawableLogo(value: String):Int = when{
    value.startsWith("btc")-> R.drawable.ic_bitcoin_logo
    value.startsWith("eth")-> R.drawable.ic_ethereum_logo
    value.startsWith("xrp")-> R.drawable.ic_ripple_xrp_logo
    value.startsWith("ltc") -> R.drawable.ic_litecoin_logo
    value.startsWith("bch") -> R.drawable.ic_bitcoin_cash_logo
    value.startsWith("tusd") -> R.drawable.ic_tusd_logo
    value.startsWith("mana") -> R.drawable.ic_mana_logo
    value.startsWith("bat") -> R.drawable.ic_bat_logo
    value.startsWith("dai") -> R.drawable.ic_dai_logo
    else -> R.drawable.ic_help_not_found

}
