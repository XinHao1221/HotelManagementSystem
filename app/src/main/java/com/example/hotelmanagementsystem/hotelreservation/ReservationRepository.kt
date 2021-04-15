package com.example.hotelmanagementsystem.hotelreservation

import androidx.compose.runtime.referentialEqualityPolicy
import androidx.lifecycle.LiveData
import androidx.lifecycle.asFlow
import com.example.hotelmanagementsystem.database.HotelDao
import com.example.hotelmanagementsystem.database.Reservation
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ReservationRepository(private val hotelDao: HotelDao) {

    val readAllData: LiveData<List<Reservation>> = hotelDao.getAllReservation()
    val todaysCheckIn: LiveData<List<Reservation>> = hotelDao.getTodaysChecKIn(getTodaysDate())
    val todaysCheckOut: LiveData<List<Reservation>> = hotelDao.getTodaysCheckKOut(getTodaysDate())
    val allCheckOut : LiveData<List<Reservation>> = hotelDao.getAllCheckKOut()
    val pendingReservation : LiveData<List<Reservation>> = hotelDao.getPendingReservation()

    suspend fun addReservation(reservation: Reservation){
        hotelDao.insertReservation(reservation)
    }

    suspend fun updateReservationStatus(status: String, reservationID: Int){
        hotelDao.updateReservationStatus(status, reservationID)
    }

    suspend fun getPendingReservation(){

    }

    suspend fun updateReservation(reservation: Reservation){
        hotelDao.updateReservation(reservation)
    }

    suspend fun deleteReservation(reservation: Reservation){
        hotelDao.deleteReservation(reservation)
    }

    suspend fun deleteAllReservation(){
        hotelDao.deleteAllReservation()
    }

    suspend fun getAllTodaysReservation(checkInDate : String){

    }

    suspend fun getAllTodaysCheckOut(checkInDate : String){

    }

    suspend fun getAllCheckOut(){

    }

    fun searchReservation(guestName:String, status: String):LiveData<List<Reservation>>{

        return hotelDao.searchReservation(guestName, status)
    }


    fun getAll():LiveData<List<Reservation>>{
        return hotelDao.getAllReservation()
    }

    // Get todaysdate
    // Use to get todays check im and check out
    fun getTodaysDate():String{

        val currentDate = LocalDate.now()

        val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val dateFormatted = currentDate.format(dateFormatter)

        return dateFormatted
    }



//    fun searchReservation(searchQuery: String): List<Reservation>{
//
//        return hotelDao.searchReservation(searchQuery)
//    }


}