package com.example.hotelmanagementsystem.database.NotesDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.hotelmanagementsystem.database.HotelDao
import com.example.hotelmanagementsystem.database.HotelDatabase

@Database(entities = [NotesEntity::class], version = 1, exportSchema = false)
abstract class NotesDatabase: RoomDatabase() {

    //abstract val sleepDatabaseDao: SleepDatabaseDao
    abstract fun notesDao() : NotesDao

    // Allow clients to access the method without instantiating the class
    companion object {

        @Volatile
        private var INSTANCE: NotesDatabase? = null

        fun getDatabase(context: Context): NotesDatabase {

            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            // Only one thread of execution is allowed at one time
            synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NotesDatabase::class.java,
                    "notes_database"
                ).build()

                INSTANCE = instance
                return instance
            }

        }
    }
}