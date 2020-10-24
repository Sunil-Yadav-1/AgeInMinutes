package com.example.ageinminutes

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener{view->
            clickDate(view)                         //we get the view and we execute something, here clickDate

        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun clickDate(view : View){
        val myCalendar = Calendar.getInstance()
        val dayofMonth = myCalendar.get(Calendar.DAY_OF_MONTH)
        val month = myCalendar.get(Calendar.MONTH)
        val year = myCalendar.get(Calendar.YEAR)

       var dpd = DatePickerDialog(this,DatePickerDialog.OnDateSetListener { view, syear, smonth, sdayOfMonth ->
            Toast.makeText(this,"The chosen date is $sdayOfMonth , month is ${smonth+1} and year is $syear",Toast.LENGTH_LONG).show()
            val selectdate = "$sdayOfMonth/${smonth+1}/$syear"
            dateenteredid.setText(selectdate)
            val sdf = SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)
            val date = sdf.parse(selectdate)
            val minutesuptodate = date!!.time/60000
            val datecurr  = sdf.parse(sdf.format(System.currentTimeMillis()))
            val minutesuptonow = datecurr!!.time/60000
            val difference = minutesuptonow - minutesuptodate
            minutesshownid.setText(difference.toString())


        }
            ,year
            ,month
            ,dayofMonth
        )
        dpd.datePicker.setMaxDate(Date().time - 86400000)
        dpd.show()

    }
}