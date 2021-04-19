package com.example.hotelmanagementsystem

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewConfiguration
import android.widget.TextView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.myapp.database.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.selects.select
import java.time.LocalDate
import java.time.Year
import java.time.format.DateTimeFormatter
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var sStaffViewModel: StaffViewModel
    private lateinit var fStaffViewModel: FloorViewModel
    private lateinit var rStaffViewModel: HotelRoomViewModel

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

        //LWH
        sStaffViewModel = ViewModelProvider(this).get(StaffViewModel::class.java)
        fStaffViewModel = ViewModelProvider(this).get(FloorViewModel::class.java)
        rStaffViewModel = ViewModelProvider(this).get(HotelRoomViewModel::class.java)
        addStaffData()
        addFloorData()
        addRoomData()
    }

    // Function to select check in date
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
    fun selectBirthday(v: View){

        val date = Calendar.getInstance()
        val year = date.get(Calendar.YEAR)
        val month = date.get(Calendar.MONTH)
        val day = date.get(Calendar.DAY_OF_MONTH)


        var birthday: TextView = findViewById(R.id.textBrithday)

        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { v, mYear, mMonth, mDay ->
            birthday.setText("" + ("%02d" .format(mDay)) + "/" + ("%02d" .format(mMonth + 1)) + "/" + mYear)

            // Code to display selected day + 1 into check out date textview
            selectedDate = ("%02d" .format(mDay)) + "/" + ("%02d" .format(mMonth + 1)) + "/" + mYear

            // Get day, month and year from the selected date
            selectedDay = selectedDate.substring(0..1)
            selectedMonth = selectedDate.substring(3..4).toString()
            selectedYear = selectedDate.substring(6..9).toString()

            // Convert into proper format so that it can be execute by parse
            selectedDate = selectedYear.toString() + "-" + selectedMonth.toString() + "-" + selectedDay.toString()

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

    fun addStaffData(){
        val staff1 = Staff("S1001","manager1","Manager","manager1@gmail.com","01012345678","1-1-2000",50.00,true,true,"123456")
        val staff2 = Staff("S1002","staff1","Staff","staff1@gmail.com","0101234567","1-2-2000",50.00,false,true,"12345")
        val staff3 = Staff("S1003","staff2","Staff","staff2@gmail.com","0101234567","1-3-2000",50.00,false,true,"1234")
        sStaffViewModel.addStaff(staff1)
        sStaffViewModel.addStaff(staff2)
        sStaffViewModel.addStaff(staff3)
    }
    fun addFloorData(){
        val floor_1 = Floor("F001","1F")
        val floor_2 = Floor("F002","2F")
        val floor_3 = Floor("F003","3F")
        fStaffViewModel.addFloor(floor_1)
        fStaffViewModel.addFloor(floor_2)
        fStaffViewModel.addFloor(floor_3)
    }
    fun addRoomData(){
        val room_1_f1 = HotelRoom("R101","F001","King Size Room","Room 101","description of room 101", 50.00, 60.00, 70.00)
        val room_2_f1 = HotelRoom("R102","F001","Single Room","Room 102","description of room 102", 50.00, 50.00, 50.00)
        val room_3_f1 = HotelRoom("R103","F001","Single Room","Room 103","description of room 103", 50.00, 50.00, 50.00)
        val room_4_f1 = HotelRoom("R104","F001","Single Room","Room 104","description of room 104", 50.00, 50.00, 50.00)
        val room_5_f1 = HotelRoom("R105","F001","Single Room","Room 105","description of room 105", 50.00, 50.00, 50.00)
        val room_1_f2 = HotelRoom("R106","F002","Single Room","Room 201","description of room 201", 50.00, 50.00, 50.00)
        val room_2_f2 = HotelRoom("R107","F002","Single Room","Room 202","description of room 202", 50.00, 50.00, 50.00)
        val room_3_f2 = HotelRoom("R108","F002","Single Room","Room 203","description of room 203", 50.00, 50.00, 50.00)
        val room_1_f3 = HotelRoom("R109","F003","Single Room","Room 301","description of room 301", 50.00, 50.00, 50.00)
        val room_2_f3 = HotelRoom("R110","F003","Single Room","Room 302","description of room 302", 50.00, 50.00, 50.00)
        rStaffViewModel.addRoom(room_1_f1)
        rStaffViewModel.addRoom(room_2_f1)
        rStaffViewModel.addRoom(room_3_f1)
        rStaffViewModel.addRoom(room_4_f1)
        rStaffViewModel.addRoom(room_5_f1)
        rStaffViewModel.addRoom(room_1_f2)
        rStaffViewModel.addRoom(room_2_f2)
        rStaffViewModel.addRoom(room_3_f2)
        rStaffViewModel.addRoom(room_1_f3)
        rStaffViewModel.addRoom(room_2_f3)

    }
}