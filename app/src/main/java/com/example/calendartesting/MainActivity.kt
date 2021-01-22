package com.example.calendartesting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import android.widget.CalendarView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException
import java.time.LocalDateTime
import java.time.LocalDateTime.*
import kotlin.math.log

/*magicni import koji nam omogucava da pozivamo sve ID-ove*/
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var kalendar: CalendarView

    /*deprecated baby*/
    /*lateinit var recycleVaktija: RecyclerView*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*deprecated baby*/
        /*recycleVaktija = findViewById(R.id.recycleVaktija)*/

        /*prosledjivanje recycle layouta ovaj context podataka*/
        recycleVaktija.layoutManager = LinearLayoutManager(this)

        /*bilo jednom davno*/
        /*recycleVaktija.adapter = MainVaktija()*/



        /*Ovde uzimamo trenutni datum i dodajemo dva dana*/
        val temp = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalDateTime.now()
        } else {
            TODO("VERSION.SDK_INT < O")
        }

        /*Pozivamo onaj haos dole.*/
        fetchJson()

    }

    /*FUnkcija za pozivanje API-a koji je kreiran kao Json*/
    private fun fetchJson() {
        d("funkcija","Pozvali metodu za fetch linka")

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

                /*POGLEDAJ U LOGCAT ZASTO PUCA
                val danasnjiDatum = feed.datum.get(0)
                tvDanasnjiDatum.text = danasnjiDatum*/

                /*obzirom da je pozadnski proces u pitanju moramo da pozivamo unutar
                * ove metode.*/
                runOnUiThread{

                    recycleVaktija.adapter = MainVaktija(feed)
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

