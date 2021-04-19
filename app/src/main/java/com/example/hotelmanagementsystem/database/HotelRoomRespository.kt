package com.example.myapp.database

import androidx.lifecycle.LiveData

class HotelRoomRespository(private val hotelRoomDao: HotelRoomDao) {
    val readAllData: LiveData<List<HotelRoom>> = hotelRoomDao.readAllData()

    suspend fun addRoom(room: HotelRoom){
        hotelRoomDao.addHotelRoom(room)
    }

    fun readAllRoom():List<HotelRoom>{
        return hotelRoomDao.readAllRoom()
    }

    fun selectRoomWithFloor(floorID:String):LiveData<List<HotelRoom>>{
        return hotelRoomDao.selectRoomWithFloor(floorID)
    }
    fun selectRoomDetails(roomID:String):HotelRoom{
        return hotelRoomDao.selectRoomDetails(roomID)
    }

    suspend fun updateRoom(room: HotelRoom){
        hotelRoomDao.updateRoom(room)
    }

    fun updateRoomDetails(roomID:String,roomName:String,roomType:String,description:String){
        return hotelRoomDao.updateRoomDetails(roomID,roomName,roomType,description)
    }

    fun updateRoomPrices(roomID:String,priceWorkday:Double,priceWeekend:Double,priceHoliday:Double){
        return hotelRoomDao.updateRoomPrices(roomID,priceWorkday,priceWeekend,priceHoliday)
    }

    fun getRoomCount(): Int{
        return hotelRoomDao.getRoomCount()
    }

}