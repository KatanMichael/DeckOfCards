package com.michaelkatan.deckofcards.controller

import android.util.Log
import com.michaelkatan.deckofcards.interfaces.DeckRequestListener
import com.michaelkatan.deckofcards.interfaces.RequestDeckInterface
import com.michaelkatan.deckofcards.model.DeckRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DeckController
{
    private val retrofitCards = Retrofit.Builder().baseUrl("https://deckofcardsapi.com/api/")
        .addConverterFactory(GsonConverterFactory.create()).build()

    private val newsClient = retrofitCards.create(RequestDeckInterface::class.java)



    fun getNewDeck(deckCount: Int, deckInterface: DeckRequestListener) : DeckRequest?
    {
        var retDeckRequest: DeckRequest? = null

        newsClient.getNewDeck(deckCount).enqueue(object : Callback<DeckRequest>
        {
            override fun onFailure(call: Call<DeckRequest>, t: Throwable)
            {
                Log.d("Retro","onFailure")

            }

            override fun onResponse(call: Call<DeckRequest>, response: Response<DeckRequest>)
            {
                Log.d("Retro","onResponse")

                if(response.isSuccessful)
                {
                    Log.d("Retro","response.isSuccessful")
                    if(response.body() != null)
                    {
                        val body = response.body()
                        Log.d("Retro","DeckCount: ${body?.remaining}")

                        deckInterface.onComplete(body)
                    }else
                    {
                        Log.d("Retro","BodyIsNull")

                    }
                }else
                {
                    Log.d("Retro","response.isNotSuccessful")
                }
            }


        })

        return retDeckRequest
    }

}