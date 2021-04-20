package com.example.hotelmanagementsystem.database.staffs

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

    @Query("UPDATE Staff SET name =:staffName, identity =:identity,email_address =:email, phoneNumber =:phone, birthday=:birthday,wage=:wage, admin=:admin,activateAcc=:activateAcc WHERE staffID = :staffID")
    fun updateStaffDetails(staffID:String,staffName:String,identity:String,email:String,phone:String,birthday:String,wage:Double,admin:Boolean,activateAcc:Boolean)
}