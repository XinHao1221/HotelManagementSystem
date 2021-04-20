package com.example.hotelmanagementsystem.database.rooms

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "HotelRoom")
data class HotelRoom(
        @PrimaryKey
        var roomID: String,
        @ColumnInfo(name = "floorID")
        var floorID: String?,
        @ColumnInfo(name = "room_type")
        var roomType: String?,
        @ColumnInfo(name = "room_name")
        var roomName: String?,
        @ColumnInfo(name = "description")
        var description: String?,
        @ColumnInfo(name = "price_Workday")
        var priceWorkday: Double?,
        @ColumnInfo(name = "price_Weekend")
        var priceWeekend: Double?,
        @ColumnInfo(name = "price_Holiday")
        var priceHoliday: Double?
)