package com.michaelkatan.deckofcards.controller

import android.util.Log
import android.widget.Toast
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
    var deckId: String = ""

    private val retrofitCards = Retrofit.Builder().baseUrl("https://deckofcardsapi.com/api/")
        .addConverterFactory(GsonConverterFactory.create()).build()

    private val cardsClient = retrofitCards.create(RequestDeckInterface::class.java)


    fun getNewDeck(deckCount: Int)
    {

        cardsClient.getNewDeck(deckCount).enqueue(object : Callback<DeckRequest> {
            override fun onFailure(call: Call<DeckRequest>, t: Throwable) {
                Log.d("Retro", "onFailure")

            }

            override fun onResponse(call: Call<DeckRequest>, response: Response<DeckRequest>) {
                Log.d("Retro", "onResponse")

                if (response.isSuccessful) {
                    val body = response.body()
                    Log.d("Retro", "response.isSuccessful")
                    if (body != null)
                    {
                        deckId = body.deck_id


                    } else {
                        Log.d("Retro", "BodyIsNull")

                    }
                } else {
                    Log.d("Retro", "response.isNotSuccessful")
                }
            }


        })

    }

    fun drawCardsFromDeck(numOfCards: Int, deckInterface: DeckRequestListener)
    {
        cardsClient.getCardsFromDeck(deckId, numOfCards).enqueue(object : Callback<DeckRequest>
        {
            override fun onFailure(call: Call<DeckRequest>, t: Throwable)
            {
                Log.d("retroCards", "onFailure")
            }

            override fun onResponse(call: Call<DeckRequest>, response: Response<DeckRequest>)
            {
                Log.d("retroCards", "onResponse")

                if(response != null)
                {
                    val body = response.body()

                    if(body != null)
                    {
                        val cards = body.cards

                        deckInterface.onComplete(body)
                        if (cards != null)
                        {
                            for(card in cards)
                            {
                                Log.d("retroCards", card.toString())
                            }
                        }else
                        {
                            Log.d("retroCards", "Cards Is Null")
                        }
                    }else
                    {
                        Log.d("retroCards", "Body Is Null")
                    }
                }
            }

        }
        )
    }

}