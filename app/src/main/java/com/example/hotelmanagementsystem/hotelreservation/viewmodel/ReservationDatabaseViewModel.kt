package com.example.hotelmanagementsystem.hotelreservation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
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

    private val repository: ReservationRepository


    init{
        val hotelDao = HotelDatabase.getDatabase(application).hotelDao()
        repository = ReservationRepository(hotelDao)
        readAllData = repository.readAllData
        todaysCheckIn = repository.todaysCheckIn
        todaysCheckOut = repository.todaysCheckOut
        allCheckOut = repository.allCheckOut
    }



    fun addReservation(reservation: Reservation){

        viewModelScope.launch(Dispatchers.IO) {
            repository.addReservation(reservation)
        }
    }

    fun updateReservation(reservation: Reservation){

        viewModelScope.launch(Dispatchers.IO) {
            repository.updateReservation(reservation)
        }
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

//    fun searchDatabase(searchQuery: String): LiveData<List<Reservation>>{
//        return repository.searchReservation(searchQuery).asLivedta()
//    }


}