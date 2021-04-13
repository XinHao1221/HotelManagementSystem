package com.example.hotelmanagementsystem.database

import androidx.lifecycle.LiveData
import androidx.room.*
import java.util.concurrent.Flow

@Dao
interface  HotelDao{

    // Reservation table
    // Insert new reservation record
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertReservation(reservation : Reservation)

    @Update
    suspend fun updateReservation(reservation : Reservation)

    @Delete
    suspend fun deleteReservation(reservation: Reservation)

    @Query("DELETE FROM reservation_table")
    suspend fun deleteAllReservation()

    @Query("SELECT * from reservation_table WHERE reservationId = :key")
    suspend fun getReservation(key: Long): Reservation?

    @Query("SELECT * from reservation_table ORDER BY reservationId ASC")
    fun getAllReservation(): LiveData<List<Reservation>>

    @Query("DELETE FROM reservation_table")
    suspend fun clear()

    @Query("SELECT * FROM reservation_table WHERE check_in_date = :checkInDate")
    fun getTodaysChecKIn(checkInDate: String): LiveData<List<Reservation>>
//    @Query("SELECT * FROM reservation_table WHERE guest_name LIKE :searchQuery")
//    fun searchReservation(searchQuery: String): Flow<List<Reservation>>


}