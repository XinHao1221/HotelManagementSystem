package com.example.myapp.database

import androidx.lifecycle.LiveData

class FloorRepository(private val floorDao: FloorDao) {
    val readAllData: LiveData<List<Floor>> = floorDao.readAllData()
    val getRoomAndFloor : LiveData<List<FloorWithRoom>> = floorDao.getRoomAndFloor()
    suspend fun addFloor(floor: Floor){
        floorDao.addFloor(floor)
    }

    fun readAllFloor():List<Floor>{
        return floorDao.readAllFloor()
    }


}