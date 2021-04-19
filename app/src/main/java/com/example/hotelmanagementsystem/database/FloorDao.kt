package com.example.myapp.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FloorDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFloor(floor: Floor)

    @Query("SELECT * FROM Floor ORDER BY floorID ASC")
    fun readAllData(): LiveData<List<Floor>>

    @Query("SELECT * FROM Floor ORDER BY floorID ASC")
    fun readAllFloor():List<Floor>

    @Transaction
    @Query("SELECT * FROM Floor ORDER BY floorID")
    fun getRoomAndFloor(): LiveData<List<FloorWithRoom>>

    @Query("SELECT COUNT(floorID) FROM Floor")
    fun getFloorCount(): Int

    @Delete
    suspend fun deleteFloor(floor: Floor)

    @Query("DELETE FROM Floor")
    suspend fun deleteAllFloor()
}