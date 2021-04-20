package com.example.hotelmanagementsystem.database.floors

import androidx.room.*
import com.example.hotelmanagementsystem.database.rooms.HotelRoom

@Entity(tableName = "Floor")
data class Floor(
        @PrimaryKey
        var floorID: String,
        @ColumnInfo(name = "Floor_name")
        var floorName: String?
)

data class FloorWithRoom(
        @Embedded val floor: Floor,
        @Relation(
                parentColumn = "floorID",
                entityColumn = "floorID"
        )
        val room: List<HotelRoom>
)