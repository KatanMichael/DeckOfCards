package com.michaelkatan.deckofcards.model

data class Card(val image: String, val value: String, val suit: String ,val code: String)
{
    override fun toString(): String {
        return "Card(image='$image', value='$value', suit='$suit', code='$code')"
    }
}