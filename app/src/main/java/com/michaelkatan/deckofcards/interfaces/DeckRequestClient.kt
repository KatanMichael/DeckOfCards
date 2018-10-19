package com.michaelkatan.deckofcards.interfaces

import com.michaelkatan.deckofcards.model.DeckRequest

interface DeckRequestListener
{
    fun onComplete(deckRequest: DeckRequest?)

}