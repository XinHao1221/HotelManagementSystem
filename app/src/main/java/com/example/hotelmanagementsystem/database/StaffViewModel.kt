package com.example.myapp.database


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StaffViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Staff>>
    private val repository: StaffRepository
    val readAllStaff: List<Staff>
        get() {
            return repository.readAllStaff()
        }


    init {
        val StaffDao = AppDataBase.getDatabase(application).getStaffDao()
        repository = StaffRepository(StaffDao)
        readAllData=repository.readAllData

    }

    fun addStaff(staff: Staff){
        viewModelScope.launch(Dispatchers.IO){
            repository.addStaff(staff)
        }
    }
    fun signUpStaff(id:String,name: String, email: String, password: String){
        repository.signUpStaff(id,name,email,password)
    }
    fun readStaffProfile(staffID:String):Staff{
        return repository.readStaffProfile(staffID)
    }
}