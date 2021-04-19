package com.example.myapp.database

import androidx.room.*

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