package com.example.hotelmanagementsystem.database.rooms

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.hotelmanagementsystem.database.rooms.HotelRoom

@Dao
interface HotelRoomDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addHotelRoom(room: HotelRoom)

    @Query("SELECT * FROM HotelRoom ORDER BY roomID ASC")
    fun readAllData(): LiveData<List<HotelRoom>>

    @Query("SELECT * FROM HotelRoom ORDER BY roomID ASC")
    fun readAllRoom():List<HotelRoom>

    @Query("SELECT * FROM HotelRoom WHERE floorID = :floorID ORDER BY roomID ASC")
    fun selectRoomWithFloor(floorID:String):LiveData<List<HotelRoom>>

    @Query("SELECT * FROM HotelRoom WHERE roomID = :roomID")
    fun selectRoomDetails(roomID:String): HotelRoom

    @Update
    fun updateRoom(vararg room: HotelRoom)

    @Query("UPDATE HotelRoom SET room_name = :roomName, room_type = :roomType,description = :description  WHERE roomID = :roomID")
    fun updateRoomDetails(roomID:String,roomName:String,roomType:String,description:String)

    @Query("UPDATE HotelRoom SET price_Workday=:priceWorkday,price_Weekend=:priceWeekend,price_Holiday=:priceHoliday  WHERE roomID = :roomID")
    fun updateRoomPrices(roomID:String,priceWorkday:Double,priceWeekend:Double,priceHoliday:Double)

    @Query("SELECT COUNT(roomID) FROM HotelRoom")
    fun getRoomCount(): Int

}