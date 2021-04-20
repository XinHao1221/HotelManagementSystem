package com.example.hotelmanagementsystem.database.lostitems

import android.graphics.Bitmap
import android.os.Parcelable
import androidx.room.*
import com.example.hotelmanagementsystem.database.converters.BitmapConverter
import com.example.hotelmanagementsystem.database.converters.DateConverter
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "lost_item_table")
data class LostItem(
        @PrimaryKey(autoGenerate = true)
        var lostItemId: Long,

        @TypeConverters(BitmapConverter::class)
        @ColumnInfo(name = "item_img")
        var itemImg: Bitmap,

        @ColumnInfo(name = "lost_location")
        var lostLocation: String,

        @ColumnInfo(name = "description")
        var description: String = "",

        @ColumnInfo(name = "found_status")
        var foundStatus: Boolean = false,

        @TypeConverters(DateConverter::class)
        @ColumnInfo(name = "found_time")
        var foundTime: Date? = null
): Parcelable