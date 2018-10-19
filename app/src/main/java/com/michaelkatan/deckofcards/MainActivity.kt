package com.michaelkatan.deckofcards

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.michaelkatan.deckofcards.controller.DeckController
import com.michaelkatan.deckofcards.interfaces.DeckRequestListener
import com.michaelkatan.deckofcards.model.DeckRequest
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.log

class MainActivity : AppCompatActivity()
{

    val deckController = DeckController()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        getNewDeckBtn.setOnClickListener()
        {
            val newDeck = deckController.getNewDeck(1,
                object : DeckRequestListener
                {
                    override fun onComplete(deckRequest: DeckRequest?)
                    {
                        if(deckRequest != null)
                        {
                            if(deckRequest.cards != null)
                            {
                                for(card in deckRequest.cards)
                                {
                                    Log.d("Retro-Card", card.toString())
                                }
                            }

                        }else
                        {
                            Log.d("Retro-Card","deckRequest Is Null")
                        }

                    }

                }
            )



        }
    }
}
