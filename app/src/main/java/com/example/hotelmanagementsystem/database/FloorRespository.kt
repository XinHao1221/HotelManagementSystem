package com.example.myapp.database

import androidx.lifecycle.LiveData
import androidx.room.Query

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