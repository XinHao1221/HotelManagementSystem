package com.example.hotelmanagementsystem.hotelreservation.viewmodel

import android.app.Application
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hotelmanagementsystem.database.HotelDatabase
import com.example.hotelmanagementsystem.database.Reservation
import com.example.hotelmanagementsystem.hotelreservation.ReservationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ReservationDatabaseViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Reservation>>
    val todaysCheckIn: LiveData<List<Reservation>>
    val todaysCheckOut: LiveData<List<Reservation>>
    val allCheckOut: LiveData<List<Reservation>>
    val allPendingReservation: LiveData<List<Reservation>>

    private val repository: ReservationRepository


    init{
        val hotelDao = HotelDatabase.getDatabase(application).hotelDao()
        repository = ReservationRepository(hotelDao)
        readAllData = repository.readAllData
        todaysCheckIn = repository.todaysCheckIn
        todaysCheckOut = repository.todaysCheckOut
        allCheckOut = repository.allCheckOut
        allPendingReservation = repository.pendingReservation
    }



    fun addReservation(reservation: Reservation){

        viewModelScope.launch(Dispatchers.IO) {
            repository.addReservation(reservation)
        }
    }

    fun checkAvailability(checkInDate: String, roomType: String): LiveData<Int>{

        return repository.checkAvailability(checkInDate, roomType)
    }

    fun updateReservation(reservation: Reservation){

        viewModelScope.launch(Dispatchers.IO) {
            repository.updateReservation(reservation)
        }
    }

    fun searchReservation(guestName: String, status: String):LiveData<List<Reservation>>{
        return repository.searchReservation(guestName, status)
    }

    fun searchTodaysCheckIn(guestName: String):LiveData<List<Reservation>>{
        return repository.searchTodaysCheckIn(guestName)
    }

    fun searchTodaysCheckOut(guestName: String):LiveData<List<Reservation>>{
        return repository.searchTodaysCheckOut(guestName)
    }


    fun deleteReservation(reservation: Reservation){

        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteReservation(reservation)
        }

    }

    fun deleteAllReservation(){

        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllReservation()
        }

    }

    fun getTodaysReservation(checkInDate : String){

        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllTodaysReservation(checkInDate)
        }

    }

    fun getTodaysCheckOut(checkOutDate : String){

        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllTodaysCheckOut(checkOutDate)
        }

    }

    fun updateReservationStatus(status: String, reservationID:Int){

        viewModelScope.launch(Dispatchers.IO) {
            repository.updateReservationStatus(status, reservationID)
        }
    }

//    fun searchDatabase(searchQuery: String): LiveData<List<Reservation>>{
//        return repository.searchReservation(searchQuery).asLivedta()
//    }


}