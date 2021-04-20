package com.example.hotelmanagementsystem.database.floors

import androidx.lifecycle.LiveData
import com.example.hotelmanagementsystem.database.floors.Floor
import com.example.hotelmanagementsystem.database.floors.FloorDao
import com.example.hotelmanagementsystem.database.floors.FloorWithRoom

class FloorRepository(private val floorDao: FloorDao) {
    val readAllData: LiveData<List<Floor>> = floorDao.readAllData()
    val getRoomAndFloor : LiveData<List<FloorWithRoom>> = floorDao.getRoomAndFloor()
    suspend fun addFloor(floor: Floor){
        floorDao.addFloor(floor)
    }

    fun readAllFloor():List<Floor>{
        return floorDao.readAllFloor()
    }
    fun getFloorCount(): Int{
        return floorDao.getFloorCount()
    }

    suspend fun deleteFloor(floor: Floor){
        floorDao.addFloor(floor)
    }

    suspend fun deleteAllFloor(){
        floorDao.deleteAllFloor()
    }
}