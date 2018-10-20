package com.michaelkatan.deckofcards.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.michaelkatan.deckofcards.R
import com.michaelkatan.deckofcards.model.Card
import com.squareup.picasso.Picasso

class RecycleAdapter(val cardsList: ArrayList<Card>) : RecyclerView.Adapter<RecycleAdapter.CardsViewHolder>()
{
    override fun onCreateViewHolder(parant: ViewGroup, p1: Int): CardsViewHolder
    {
        val view = LayoutInflater.from(parant.context).inflate(R.layout.card_layout,parant,false)

        return CardsViewHolder(view)

    }

    override fun getItemCount(): Int
    {
        return cardsList.size
    }

    override fun onBindViewHolder(holder: CardsViewHolder, p1: Int)
    {
        val card = cardsList[p1]

        Picasso.get().load(card.image).into(holder.cardImage)

    }


    inner class CardsViewHolder(val myView: View): RecyclerView.ViewHolder(myView)
    {
        val cardImage : ImageView = myView.findViewById(R.id.cardImage)
    }

}