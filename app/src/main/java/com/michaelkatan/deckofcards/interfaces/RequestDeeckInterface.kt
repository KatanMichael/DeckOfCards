package com.michaelkatan.deckofcards.interfaces

import com.michaelkatan.deckofcards.model.DeckRequest
import retrofit2.http.GET
import retrofit2.http.Query

interface RequestDeckInterface
{
    @GET("deck/new/shuffle/")
    fun getNewDeck(@Query("deck_count ") deckCount: Int) : retrofit2.Call<DeckRequest>

}