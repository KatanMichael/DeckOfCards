package com.michaelkatan.deckofcards

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.michaelkatan.deckofcards.adapter.RecycleAdapter
import com.michaelkatan.deckofcards.controller.DeckController
import com.michaelkatan.deckofcards.interfaces.DeckRequestListener
import com.michaelkatan.deckofcards.model.Card
import com.michaelkatan.deckofcards.model.DeckRequest
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
{

    val deckController = DeckController()
    val cardsList = ArrayList<Card>()

    val adapter = RecycleAdapter(cardsList)
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        deckRecyclerView.adapter = adapter

        deckRecyclerView.layoutManager = GridLayoutManager(this,2)

        getNewDeckBtn.setOnClickListener()
        {
            deckController.getNewDeck(1)
        }

        getCards.setOnClickListener()
        {
            val numCards = numOfCards.text.toString().toInt()

            deckController.drawCardsFromDeck(numCards, object : DeckRequestListener
            {
                override fun onComplete(deckRequest: DeckRequest?)
                {
                    if(deckRequest != null)
                    {
                        val cards = deckRequest.cards

                        for(card in cards)
                        {
                            Log.d("RetroMain","Main: ${card.toString()}")
                            cardsList.add(card)

                        }
                        Toast.makeText(this@MainActivity,
                            "${deckRequest.remaining} Cards left in deck"
                            ,Toast.LENGTH_SHORT).show()

                        adapter.notifyDataSetChanged()
                    }

                }
            }

            )

        }
    }
}
