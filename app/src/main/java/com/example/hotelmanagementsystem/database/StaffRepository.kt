package com.example.myapp.database

import androidx.lifecycle.LiveData

class StaffRepository(private val staffDao: StaffDao) {
    val readAllData: LiveData<List<Staff>> = staffDao.readAllData()

    suspend fun addStaff(staff: Staff){
        staffDao.addStaff(staff)
    }

    fun readAllStaff():List<Staff>{
        return staffDao.readAllStaff()
    }
    fun signUpStaff(id:String,name:String,email:String,password:String){
        staffDao.signUpStaff(id,name,email,password)
    }
    fun readStaffProfile(staffID:String):Staff{
        return staffDao.readStaffProfile(staffID)
    }
    fun updateStaffDetails(staffID:String,staffName:String,identity:String,email:String,phone:String,birthday:String,wage:Double,admin:Boolean,activateAcc:Boolean){
        return staffDao.updateStaffDetails(staffID,staffName,identity,email,phone,birthday,wage,admin,activateAcc)
    }
}