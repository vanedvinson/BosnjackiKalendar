package com.example.calendartesting

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.dogadjaji_grid.view.*


class MainDogadjaji(val feed: FeedDogadjaji) : RecyclerView.Adapter<CustomViewHolderTwo>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolderTwo {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.dogadjaji_grid, parent,false)
        return CustomViewHolderTwo(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolderTwo, position: Int) {
        val dogadjaj = feed.GregorijanskiKalendar.get(position)

        holder.view.tvDogadjaj.text = dogadjaj.naslov
        holder.view.tvDatum.text = dogadjaj.datum

    }

    override fun getItemCount(): Int {
        return 3;
    }

}
class CustomViewHolderTwo(val view: View): RecyclerView.ViewHolder(view){

}