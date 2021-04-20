package com.example.hotelmanagementsystem.database.reservations

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface  ReservationDao{

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

    @Query("SELECT * FROM reservation_table WHERE check_in_date = :checkInDate AND status = 'pending'")
    fun getTodaysChecKIn(checkInDate: String): LiveData<List<Reservation>>

    @Query("SELECT * FROM reservation_table WHERE status = 'pending'")
    fun getPendingReservation(): LiveData<List<Reservation>>

    @Query("SELECT * FROM reservation_table WHERE check_out_date = :checkOutDate AND status = 'checkIn'")
    fun getTodaysCheckKOut(checkOutDate: String): LiveData<List<Reservation>>

    @Query("SELECT * FROM reservation_table WHERE status = 'checkIn'")
    fun getAllCheckOut(): LiveData<List<Reservation>>

    @Query("SELECT * FROM reservation_table WHERE status = 'checkOut'")
    fun getAllCheckKOut(): LiveData<List<Reservation>>

    @Query("UPDATE reservation_table SET status = :status WHERE reservationId = :reservationID")
    fun updateReservationStatus(status: String, reservationID : Int)

    @Query("SELECT * FROM reservation_table WHERE UPPER(guest_name) LIKE :searchQuery AND status = :status")
    fun searchReservation(searchQuery: String, status: String): LiveData<List<Reservation>>

    @Query("SELECT * FROM reservation_table WHERE check_in_date = :checkInDate AND status = 'pending' AND UPPER(guest_name) LIKE :guestName")
    fun searchTodaysCheckIn(guestName: String, checkInDate: String): LiveData<List<Reservation>>

    @Query("SELECT * FROM reservation_table WHERE check_out_date = :checkOutDate AND status = 'checkIn' AND UPPER(guest_name) LIKE :guestName")
    fun searchTodaysCheckOut(guestName: String, checkOutDate: String): LiveData<List<Reservation>>

    @Query("SELECT COUNT(*) FROM reservation_table WHERE check_in_date = :checkInDate AND room_type LIKE :roomType AND status IN ('pending', 'checkIn')")
    fun checkAvailability(checkInDate: String, roomType: String): LiveData<Int>

    //Lost Item Table

}