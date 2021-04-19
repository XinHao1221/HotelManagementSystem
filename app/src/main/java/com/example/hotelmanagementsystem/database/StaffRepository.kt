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
}