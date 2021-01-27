package com.example.calendartesting

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_calendar.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.ExportButton
import kotlinx.android.synthetic.main.activity_main.InfoButton
import kotlinx.android.synthetic.main.activity_main.SettingsButton
import ru.cleverpumpkin.calendar.CalendarDate
import ru.cleverpumpkin.calendar.CalendarView
import java.time.Year
import java.util.*

class CalendarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        val calendarView = calendar_view
        val calendar = Calendar.getInstance()

        // Initial date
        calendar.set(Calendar.getInstance().get(Calendar.YEAR),
            Calendar.getInstance().get(Calendar.MONTH),
            Calendar.getInstance().get(Calendar.DATE))
        val initialDate = CalendarDate(calendar.time)

        // Minimum available date
        calendar.set(2010, Calendar.JANUARY, 31)
        val minDate = CalendarDate(calendar.time)

        // Maximum available date
        calendar.set(Calendar.getInstance().get(Calendar.YEAR), Calendar.DECEMBER, 31)
        val maxDate = CalendarDate(calendar.time)

        /*List of preselected dates that will be initially selected
        val preselectedDates: List<CalendarDate> = getPreselectedDates()*/

        // The first day of week
        val firstDayOfWeek = java.util.Calendar.MONDAY

        // Set up calendar with all available parameters
        calendarView.setupCalendar(
            initialDate = initialDate,
            minDate = minDate,
            maxDate = maxDate,
            selectionMode = CalendarView.SelectionMode.NONE,
            /*selectedDates = preselectedDates,*/
            firstDayOfWeek = firstDayOfWeek,
            showYearSelectionView = true
        )




        //this is meant for the top bar back button
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()?.setDisplayShowHomeEnabled(true);


        //Bottom bar buttons
        HomeButton.setOnClickListener(){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        InfoButton.setOnClickListener(){
            val intent = Intent(this, OAplikaciji::class.java)
            startActivity(intent)
        }
        /*CalendarButton.setOnClickListener(){
            val intent = Intent(this, CalendarActivity::class.java)
            startActivity(intent)
        }*/
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