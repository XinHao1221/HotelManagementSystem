package com.example.myapp.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HotelRoomViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<HotelRoom>>
    private val repository: HotelRoomRespository

    init {
        val HotelRoomDao = AppDataBase.getDatabase(application).getHotelRoomDao()
        repository = HotelRoomRespository(HotelRoomDao)
        readAllData=repository.readAllData

    }

    fun addRoom(room: HotelRoom){
        viewModelScope.launch(Dispatchers.IO){
            repository.addRoom(room)
        }
    }

    fun selectRoomWithFloor(floorID:String):LiveData<List<HotelRoom>>{
        return repository.selectRoomWithFloor(floorID)
    }
    fun selectRoomDetails(roomID: String): HotelRoom{
        return repository.selectRoomDetails(roomID)
    }
    fun updateRoom(room: HotelRoom){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateRoom(room)
        }
    }

    fun updateRoomDetails(roomID:String,roomName:String,roomType:String,description:String){
        return repository.updateRoomDetails(roomID,roomName,roomType,description)
    }

    fun updateRoomPrices(roomID:String,priceWorkday:Double,priceWeekend:Double,priceHoliday:Double){
        return repository.updateRoomPrices(roomID, priceWorkday, priceWeekend, priceHoliday)
    }

}