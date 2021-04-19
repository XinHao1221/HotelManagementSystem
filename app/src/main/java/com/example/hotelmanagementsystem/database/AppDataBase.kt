package com.example.myapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Staff::class,Floor::class,HotelRoom::class], version = 2,exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    abstract fun getStaffDao(): StaffDao
    abstract fun getFloorDao(): FloorDao
    abstract fun getHotelRoomDao(): HotelRoomDao

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