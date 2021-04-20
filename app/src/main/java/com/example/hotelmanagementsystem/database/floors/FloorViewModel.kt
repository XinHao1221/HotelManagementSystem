package com.example.hotelmanagementsystem.database.floors

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.myapp.database.AppDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FloorViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Floor>>
    private val repository: FloorRepository
    val getRoomAndFloor: LiveData<List<FloorWithRoom>>
        get() {
            return repository.getRoomAndFloor
        }
    val readAllFloor: List<Floor>
        get() {
            return repository.readAllFloor()
        }


    init {
        val FloorDao = AppDataBase.getDatabase(application).getFloorDao()
        repository = FloorRepository(FloorDao)
        readAllData=repository.readAllData

    }

    fun addFloor(floor: Floor){
        viewModelScope.launch(Dispatchers.IO){
            repository.addFloor(floor)
        }
    }
    fun getFloorCount(): Int{
        return repository.getFloorCount()
    }
    fun deleteFloor(floor: Floor){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteFloor(floor)
        }
    }
    fun deleteAllFloor(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAllFloor()
        }
    }
}