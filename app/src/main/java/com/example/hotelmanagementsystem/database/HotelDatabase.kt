package com.example.hotelmanagementsystem.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//@Database(entities = [Reservation::class], version = 1, exportSchema = false)
@Database(entities = [Reservation::class], version = 1, exportSchema = false)
abstract class HotelDatabase: RoomDatabase() {

    //abstract val sleepDatabaseDao: SleepDatabaseDao
    abstract fun hotelDao() : HotelDao

    // Allow clients to access the method without instantiating the class
    companion object {

        @Volatile
        private var INSTANCE: HotelDatabase? = null

        fun getDatabase(context: Context): HotelDatabase {

            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            // Only one thread of execution is allowed at one time
            synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HotelDatabase::class.java,
                    "hotel_database"
                ).build()

                INSTANCE = instance
                return instance
            }

        }
    }

}


