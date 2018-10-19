package com.michaelkatan.deckofcards.model

class DeckRequest(val success: Boolean, val deck_id: String, val shuffled: Boolean, val remaining: Int
                        ,val cards: Array<Card>)