package com.mwdevs.capstone.coins.domain.use_case

import android.util.Log
import com.mwdevs.capstone.R
import com.mwdevs.capstone.coins.data.remote.model.Payload
import com.mwdevs.capstone.coins.data.remote.model.ResponseModel
import com.mwdevs.capstone.coins.domain.delegate.BooksEnum
import com.mwdevs.capstone.coins.domain.model.CoinUIModel
import com.mwdevs.capstone.utils.APIServiceBuilder
import com.mwdevs.capstone.utils.retrofit.models.ResponseHandler

class GetBookListUseCase {
    val repo = APIServiceBuilder()
    suspend operator fun invoke(): ResponseHandler<List<CoinUIModel>?>{
        val response = repo.getTest()
        if (response is ResponseHandler.Success){
            return ResponseHandler.Success(
                data = ResponseModel(
                    success = response.data?.success == true,
                    successBody = mapToUiModel(response)
                )
            )
        }
        return ResponseHandler.Error(
            data = ResponseModel(
                success = false,
                errorBody = response.data?.errorBody,
                successBody = null
            ),
            errorBody = null
        )
    }

    private fun mapToUiModel(response: ResponseHandler.Success<List<Payload>?>): List<CoinUIModel>?{
        return response.data?.successBody?.take(29)?.map { model ->
            try {
                CoinUIModel(
                    id = model.book,
                    coinResource = getCoinResource(BooksEnum.valueOf(model.book))
                )
            }catch (e: Exception){
                Log.e("Exception", e.message.toString())
                CoinUIModel(
                    id = model.book,
                    coinResource = getCoinResource(BooksEnum.valueOf(model.book))
                )
            }
        }
    }
}

private fun getCoinName(value:String): String = when(value){
    "btc"->"Bitcoin"
    "eth"-> "Ethereum"
    "xrp"-> "XRP"
    "ltc"-> "Litecoin"
    "bch"-> "Bitcoin Cash"
    "tusd"-> "TUSD"
    "mana"-> "MANA"
    "dai"-> "Dai"
    else->""
}

private fun getCoinResource(value: BooksEnum): Map<Int, List<Int>> =
    when (value){
        BooksEnum.btc_mxn -> mapOf(R.string.btc_name to listOf(R.drawable.ic_bitcoin_logo, R.drawable.ic_flag_mexico))
        BooksEnum.eth_btc -> mapOf(R.string.eth_name to listOf(R.drawable.ic_ethereum_logo, R.drawable.ic_bitcoin_logo))
        BooksEnum.eth_mxn -> mapOf(R.string.eth_name to listOf(R.drawable.ic_ethereum_logo, R.drawable.ic_flag_mexico))
        BooksEnum.xrp_btc -> mapOf(R.string.xrp_name to listOf(R.drawable.ic_ripple_xrp_logo, R.drawable.ic_bitcoin_logo))
        BooksEnum.xrp_mxn -> mapOf(R.string.xrp_name to listOf(R.drawable.ic_ripple_xrp_logo, R.drawable.ic_flag_mexico))
        BooksEnum.ltc_btc -> mapOf(R.string.ltc_name to listOf(R.drawable.ic_litecoin_logo, R.drawable.ic_bitcoin_logo))
        BooksEnum.bch_btc -> mapOf(R.string.bch_name to listOf(R.drawable.ic_bitcoin_cash_logo, R.drawable.ic_bitcoin_logo))
        BooksEnum.bch_mxn -> mapOf(R.string.bch_name to listOf(R.drawable.ic_bitcoin_cash_logo, R.drawable.ic_flag_mexico))
        BooksEnum.tusd_btc -> mapOf(R.string.tusd_name to listOf(R.drawable.ic_tusd_logo, R.drawable.ic_bitcoin_logo))
        BooksEnum.tusd_mxn -> mapOf(R.string.tusd_name to listOf(R.drawable.ic_tusd_logo, R.drawable.ic_flag_mexico))
        BooksEnum.mana_btc -> mapOf(R.string.mana_name to listOf(R.drawable.ic_mana_logo, R.drawable.ic_bitcoin_logo))
        BooksEnum.mana_mxn -> mapOf(R.string.mana_name to listOf(R.drawable.ic_mana_logo, R.drawable.ic_flag_mexico))
        BooksEnum.bat_btc -> mapOf(R.string.bat_name to listOf(R.drawable.ic_bat_logo, R.drawable.ic_bitcoin_logo))
        BooksEnum.bat_mxn -> mapOf(R.string.bat_name to listOf(R.drawable.ic_bat_logo, R.drawable.ic_flag_mexico))
        BooksEnum.btc_ars -> mapOf(R.string.btc_name to listOf(R.drawable.ic_bitcoin_logo, R.drawable.ic_flag_argentina))
        BooksEnum.btc_dai -> mapOf(R.string.btc_name to listOf(R.drawable.ic_bitcoin_logo, R.drawable.ic_dai_logo))
        BooksEnum.dai_mxn -> mapOf(R.string.dai_name to listOf(R.drawable.ic_dai_logo, R.drawable.ic_flag_mexico))
        BooksEnum.btc_usd -> mapOf(R.string.btc_name to listOf(R.drawable.ic_bitcoin_logo, R.drawable.ic_flag_usa))
        BooksEnum.xrp_usd -> mapOf(R.string.xrp_name to listOf(R.drawable.ic_ripple_xrp_logo, R.drawable.ic_flag_usa))
        BooksEnum.eth_usd -> mapOf(R.string.eth_name to listOf(R.drawable.ic_ethereum_logo, R.drawable.ic_flag_usa))
        BooksEnum.dai_ars -> mapOf(R.string.dai_name to listOf(R.drawable.ic_dai_logo, R.drawable.ic_flag_argentina))
        BooksEnum.btc_brl -> mapOf(R.string.btc_name to listOf(R.drawable.ic_bitcoin_logo, R.drawable.ic_flag_brazil))
        BooksEnum.eth_ars -> mapOf(R.string.eth_name to listOf(R.drawable.ic_ethereum_logo, R.drawable.ic_flag_argentina))
        BooksEnum.eth_brl -> mapOf(R.string.eth_name to listOf(R.drawable.ic_ethereum_logo, R.drawable.ic_flag_brazil))
        BooksEnum.btc_usdt -> mapOf(R.string.btc_name to listOf(R.drawable.ic_bitcoin_logo, R.drawable.ic_help_not_found)) // usdt -> Tether
        BooksEnum.usd_mxn -> mapOf(R.string.usd_name to listOf(R.drawable.ic_flag_usa, R.drawable.ic_flag_mexico))
        BooksEnum.usd_ars -> mapOf(R.string.usd_name to listOf(R.drawable.ic_flag_usa, R.drawable.ic_flag_argentina))
        BooksEnum.usd_brl -> mapOf(R.string.usd_name to listOf(R.drawable.ic_flag_usa, R.drawable.ic_flag_brazil))
        BooksEnum.mana_usd -> mapOf(R.string.mana_name to listOf(R.drawable.ic_mana_logo, R.drawable.ic_flag_usa))
        BooksEnum.ltc_usd -> mapOf(R.string.ltc_name to listOf(R.drawable.ic_litecoin_logo, R.drawable.ic_flag_usa))
        BooksEnum.ltc_mxn -> mapOf(R.string.ltc_name to listOf(R.drawable.ic_litecoin_logo, R.drawable.ic_flag_mexico))
        else-> mapOf(R.string.unknown to  listOf(R.drawable.ic_help_not_found, R.drawable.ic_help_not_found))
    }