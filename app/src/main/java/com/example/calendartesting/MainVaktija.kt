package com.example.calendartesting

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.vaktijagrid.view.*


class MainVaktija(val feed: Feed) : RecyclerView.Adapter<CustomViewHolder>() {

    /*igranje*/
    val vremena = listOf("buhu")


    override fun getItemCount(): Int {
        return 1
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.vaktijagrid, parent,false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        /*OVDE JE VISE NEGO OCIGLEDNO STA SE DESAVA*/
        val vreme = vremena.get(0)
        val vremeZore = feed.vakat.get(0)
        val vremeIzlaskaSunca = feed.vakat.get(1)
        val vremePodne = feed.vakat.get(2)
        val vremeIkindija = feed.vakat.get(3)
        val vremeAksama = feed.vakat.get(4)
        val vremeJacije = feed.vakat.get(5)

        //val danasnjiDatum = feed.datum.get(0)

        holder?.view?.tvZaIzlazakSunca.text = vremeZore
        holder?.view?.tvZaSabah.text = vremeIzlaskaSunca
        holder?.view?.tvZaPodne.text = vremePodne
        holder?.view?.tvZaIkindiju.text = vremeIkindija
        holder?.view?.tvZaAksam.text = vremeAksama
        holder?.view?.tvZaJaciju.text = vremeJacije

        //holder?.view?.tvDanasnjiDatum.text = danasnjiDatum
    }
}

class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view){

}