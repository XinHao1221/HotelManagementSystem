package com.example.hotelmanagementsystem.database.converters

import androidx.room.TypeConverter
import java.util.*

public class DateConverter {
    @TypeConverter
    fun toDate(dateLong: Long): Date? {
        return if (dateLong == 0.toLong()) {
            null
        } else {
            dateLong?.let { Date(it) }
        }
    }

    @TypeConverter
    fun fromDate(date: Date?): Long {
        return if (date == null) {
            0.toLong()
        } else {
            date?.time
        }
    }
}
