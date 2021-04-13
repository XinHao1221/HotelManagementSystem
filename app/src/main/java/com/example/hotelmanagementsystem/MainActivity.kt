package com.example.hotelmanagementsystem

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewConfiguration
import android.widget.TextView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.selects.select
import java.time.LocalDate
import java.time.Year
import java.time.format.DateTimeFormatter
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var drawerLayout: DrawerLayout

    private lateinit var listener: NavController.OnDestinationChangedListener

    private lateinit var selectedDate:String
    private lateinit var selectedDay:String
    private lateinit var selectedMonth:String
    private lateinit var selectedYear: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = findNavController(R.id.nav_host_fragment)
        drawerLayout = findViewById(R.id.drawer_layout)
        main_nav_drawer.setupWithNavController(navController)

        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)

        setupActionBarWithNavController(navController, appBarConfiguration)

    }

    // Functiom to select check in date
    fun selectCheckInDate(v: View){

        val date = Calendar.getInstance()
        val year = date.get(Calendar.YEAR)
        val month = date.get(Calendar.MONTH)
        val day = date.get(Calendar.DAY_OF_MONTH)


        var checkInDate: TextView = findViewById(R.id.check_in_date)
        var checkOutDate:TextView = findViewById(R.id.check_out_date)


        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { v, mYear, mMonth, mDay ->
            checkInDate.setText("" + ("%02d" .format(mDay)) + "/" + ("%02d" .format(mMonth + 1)) + "/" + mYear)

            // Code to display selected day + 1 into check out date textview
            selectedDate = ("%02d" .format(mDay)) + "/" + ("%02d" .format(mMonth + 1)) + "/" + mYear

            // Get day, month and year from the selected date
            selectedDay = selectedDate.substring(0..1)
            selectedMonth = selectedDate.substring(3..4).toString()
            selectedYear = selectedDate.substring(6..9).toString()

            // Convert into proper format so that it can be execute by parse
            selectedDate = selectedYear.toString() + "-" + selectedMonth.toString() + "-" + selectedDay.toString()

            // Convert string into Date
            val formatedDate = LocalDate.parse(selectedDate, DateTimeFormatter.ISO_DATE)

            // Add date plus one
            var newDate = formatedDate.plusDays(1);

            // Convert back in to standard format dd/mm/yyyy
            val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            val checkOutDateFormatted = newDate.format(dateFormatter)

            // Set the next day into check out date textview
            checkOutDate.setText(checkOutDateFormatted.toString())
                                                                            }, year, month, day)

        dpd.show()


    }

    // function to select check out date
    fun selectCheckOutDate(v: View){

        val date = Calendar.getInstance()
        val year = date.get(Calendar.YEAR)
        val month = date.get(Calendar.MONTH)
        val day = date.get(Calendar.DAY_OF_MONTH)

        var text: TextView = findViewById(R.id.check_out_date)

        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { v, mYear, mMonth, mDay ->
            text.setText("" + ("%02d" .format(mDay)) + "/" + ("%02d" .format(mMonth + 1)) + "/" + mYear)}, year, month, day)

        dpd.show()

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)

        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}