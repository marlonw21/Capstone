package com.mwdevs.capstone.coins.domain.delegate

import android.content.res.Resources
import com.mwdevs.capstone.coins.data.remote.model.Payload
import com.mwdevs.capstone.coins.domain.model.CoinUIModel
import com.mwdevs.capstone.utils.retrofit.models.ResponseHandler

class BookListDelegateImp(resources: Resources): BookListDelegate<ResponseHandler<List<Payload>>> {

    override suspend fun toUiModel(apiResponse: ResponseHandler<List<Payload>>?) {
       val copy = apiResponse?.data?.successBody?.map {
           it.copy()
       }
    }
}

enum class BooksEnum{
    btc_mxn,
    eth_btc,
    eth_mxn,
    xrp_btc,
    xrp_mxn,
    ltc_btc,
    bch_btc,
    bch_mxn,
    tusd_btc,
    tusd_mxn,
    mana_btc,
    mana_mxn,
    bat_btc,
    bat_mxn,
    btc_ars,
    btc_dai,
    dai_mxn,
    btc_usd,
    xrp_usd,
    eth_usd,
    dai_ars,
    btc_brl,
    eth_ars,
    eth_brl,
    btc_usdt,
    usd_mxn,
    usd_ars,
    usd_brl,
    mana_usd,
    ltc_usd,
    ltc_mxn;

}