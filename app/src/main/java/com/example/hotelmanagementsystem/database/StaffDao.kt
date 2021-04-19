package com.example.myapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface StaffDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addStaff(staff: Staff)

    @Query("SELECT * FROM Staff ORDER BY staffID ASC")
    fun readAllData():LiveData<List<Staff>>

    @Query("SELECT * FROM Staff ORDER BY staffID ASC")
    fun readAllStaff():List<Staff>

    @Query("INSERT INTO Staff (staffID,name,email_address,password) VALUES (:id,:name,:email,:password)")
    fun signUpStaff(id:String,name:String,email:String,password:String)

    @Query("SELECT * FROM Staff WHERE staffID=:staffID ORDER BY staffID ASC")
    fun readStaffProfile(staffID:String):Staff
}