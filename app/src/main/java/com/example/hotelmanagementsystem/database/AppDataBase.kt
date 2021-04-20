package com.example.myapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.hotelmanagementsystem.database.reservations.Reservation
import com.example.hotelmanagementsystem.database.reservations.ReservationDao
import com.example.hotelmanagementsystem.database.converters.BitmapConverter
import com.example.hotelmanagementsystem.database.converters.DateConverter
import com.example.hotelmanagementsystem.database.floors.Floor
import com.example.hotelmanagementsystem.database.floors.FloorDao
import com.example.hotelmanagementsystem.database.lostitems.LostItem
import com.example.hotelmanagementsystem.database.lostitems.LostItemDao
import com.example.hotelmanagementsystem.database.rooms.HotelRoom
import com.example.hotelmanagementsystem.database.rooms.HotelRoomDao
import com.example.hotelmanagementsystem.database.staffs.Staff
import com.example.hotelmanagementsystem.database.staffs.StaffDao

@Database(entities = [Staff::class, Floor::class, HotelRoom::class, LostItem::class, Reservation::class], version = 2,exportSchema = false)
@TypeConverters(DateConverter::class, BitmapConverter::class)
abstract class AppDataBase : RoomDatabase() {
    abstract fun getStaffDao(): StaffDao
    abstract fun getFloorDao(): FloorDao
    abstract fun getHotelRoomDao(): HotelRoomDao
    abstract fun getLostItemDao(): LostItemDao
    abstract fun getReservationDao(): ReservationDao

    companion object {

        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDatabase(context: Context): AppDataBase {

            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            // Only one thread of execution is allowed at one time
            synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "hotel_database"
                ).allowMainThreadQueries().fallbackToDestructiveMigration().build()

                INSTANCE = instance
                return instance
            }
        }
    }

}