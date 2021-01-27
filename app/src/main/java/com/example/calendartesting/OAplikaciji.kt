package com.example.calendartesting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_calendar.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.CalendarButton
import kotlinx.android.synthetic.main.activity_main.ExportButton
import kotlinx.android.synthetic.main.activity_main.SettingsButton

class OAplikaciji : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_o_aplikaciji)

        //this is meant for the top bar back button
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()?.setDisplayShowHomeEnabled(true);


        //Bottom bar buttons
        HomeButton.setOnClickListener(){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        /*InfoButton.setOnClickListener(){
            val intent = Intent(this, OAplikaciji::class.java)
            startActivity(intent)
        }*/
        CalendarButton.setOnClickListener(){
            val intent = Intent(this, CalendarActivity::class.java)
            startActivity(intent)
        }
        ExportButton.setOnClickListener(){
            val intent = Intent(this, ExportActivity::class.java)
            startActivity(intent)
        }
        SettingsButton.setOnClickListener(){
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}