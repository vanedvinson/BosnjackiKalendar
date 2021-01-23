package com.example.calendartesting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import android.widget.CalendarView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException

/*magicni import koji nam omogucava da pozivamo sve ID-ove*/
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var kalendar: CalendarView

    /*deprecated baby*/
    /*lateinit var recycleVaktija: RecyclerView*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.getSupportActionBar()?.hide()

        /*prosledjivanje recycle layouta ovaj context podataka*/
        recycleVaktija.layoutManager = LinearLayoutManager(this)
        recycleDogadjaji.layoutManager = LinearLayoutManager(this)

        /*Pozivamo onaj haos dole.*/
        fetchJson()
        fetchDogadjaj()

    }

    private fun fetchDogadjaj() {
        d("funkcijaZaDogadjaje", "pozvali metodu za fetch linka dogadjaja")

        val url ="https://pastebin.com/raw/kcf6XjLU"

        /*kreiranje promenljive za poziv*/
        val poziv = Request.Builder().url(url).build()

        /*Uz pomoc ove promenljive mozemo da napravimo URL request*/
        val klijent =  OkHttpClient()

        klijent.newCall(poziv).enqueue(object: Callback{
            override fun onFailure(call: Call, e: IOException) {
                d("funkcija","Neuspesno izvrsavanje funckije")
            }

            override fun onResponse(call: Call, response: Response) {
                d("funkcijaDogadjaji", "dobili response")
                val body = response?.body?.string()
                println(body)

                val gson = GsonBuilder().create()
                val feed = gson.fromJson(body, FeedDogadjaji::class.java)

                runOnUiThread{
                    recycleDogadjaji.adapter = MainDogadjaji(feed)
                }
            }
        })
    }

    /*FUnkcija za pozivanje API-a koji je kreiran kao Json*/
    public fun fetchJson() {
        d("funkcija","Pozvali metodu za fetch linka vaktije")

        /*link sa API-jem*/
        val url = "https://api.vaktija.ba/vaktija/v1/110"

        /*kreiranje promenljive za poziv*/
        val poziv = Request.Builder().url(url).build()

        /*Uz pomoc ove promenljive mozemo da napravimo URL request*/
        val klijent =  OkHttpClient()

        klijent.newCall(poziv).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response) {
                d("funkcija","Uspesno izvrsavanje funckije")
                val body = response?.body?.string()
                println(body)

                val gson = GsonBuilder().create()
                //generisemo niz u pomoc gson builder-a
                val feed = gson.fromJson(body, Feed::class.java)

                /*obzirom da je pozadnski proces u pitanju moramo da pozivamo unutar
                * ove metode.*/
                runOnUiThread{

                    recycleVaktija.adapter = MainVaktija(feed)

                    val danasnjiDatum = feed.datum.get(0)
                    tvDanasnjiDatum.text = danasnjiDatum//.toString()
                }

            }
            override fun onFailure(call: Call, e: IOException) {
                d("funkcija","Neuspesno izvrsavanje funckije")
            }
        })
    }
}
//Konstruktor klasa
class Feed(val vakat: List<String>, val datum: List<String>)

class FeedDogadjaji(val GregorijanskiKalendar : List<GregorijanskiKalendar>)
class GregorijanskiKalendar(val naslov : String, val datum : String)
//json file za kalendar
//https://pastebin.com/raw/kcf6XjLU