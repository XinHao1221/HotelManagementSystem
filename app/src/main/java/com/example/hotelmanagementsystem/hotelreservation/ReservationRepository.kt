package com.example.hotelmanagementsystem.hotelreservation

import androidx.lifecycle.LiveData
import com.example.hotelmanagementsystem.database.reservations.ReservationDao
import com.example.hotelmanagementsystem.database.reservations.Reservation
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ReservationRepository(private val reservationDao: ReservationDao) {

    val readAllData: LiveData<List<Reservation>> = reservationDao.getAllReservation()
    val todaysCheckIn: LiveData<List<Reservation>> = reservationDao.getTodaysChecKIn(getTodaysDate())
    //val todaysCheckOut: LiveData<List<Reservation>> = hotelDao.getTodaysCheckKOut(getTodaysDate())
    val todaysCheckOut: LiveData<List<Reservation>> = reservationDao.getAllCheckOut()
    val allCheckOut : LiveData<List<Reservation>> = reservationDao.getAllCheckKOut()
    val pendingReservation : LiveData<List<Reservation>> = reservationDao.getPendingReservation()

    suspend fun addReservation(reservation: Reservation){
        reservationDao.insertReservation(reservation)
    }

    suspend fun updateReservationStatus(status: String, reservationID: Int){
        reservationDao.updateReservationStatus(status, reservationID)
    }

    suspend fun getPendingReservation(){

    }

    suspend fun updateReservation(reservation: Reservation){
        reservationDao.updateReservation(reservation)
    }

    suspend fun deleteReservation(reservation: Reservation){
        reservationDao.deleteReservation(reservation)
    }

    suspend fun deleteAllReservation(){
        reservationDao.deleteAllReservation()
    }

    fun checkAvailability(checkInDate: String, roomType: String): LiveData<Int>{

        return reservationDao.checkAvailability(checkInDate, roomType)

    }
    suspend fun getAllTodaysReservation(checkInDate : String){

    }

    suspend fun getAllTodaysCheckOut(checkInDate : String){

    }

    suspend fun getAllCheckOut(){

    }

    fun searchReservation(guestName:String, status: String):LiveData<List<Reservation>>{

        return reservationDao.searchReservation(guestName, status)
    }

    fun searchTodaysCheckIn(guestName: String):LiveData<List<Reservation>>{

        return reservationDao.searchTodaysCheckIn(guestName, getTodaysDate())

    }

    fun searchTodaysCheckOut(guestName: String):LiveData<List<Reservation>>{

        return reservationDao.searchTodaysCheckOut(guestName, getTodaysDate())

    }


    fun getAll():LiveData<List<Reservation>>{
        return reservationDao.getAllReservation()
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