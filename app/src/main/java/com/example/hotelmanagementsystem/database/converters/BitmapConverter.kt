package com.example.hotelmanagementsystem.database.converters

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import androidx.room.TypeConverter
import java.io.ByteArrayOutputStream

public class BitmapConverter {
    @TypeConverter
    fun toBitmap(encodedString:String): Bitmap? {
        return try {
            var encodeByte: ByteArray = Base64.decode(encodedString, Base64.DEFAULT);
            var bitmap: Bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.size);
            bitmap

        } catch(e: Exception) {
            e.message;
            null;
        }
    }

    @TypeConverter
    fun fromBitmap(bitmap: Bitmap): String? {
        var baos: ByteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos)
        var b: ByteArray? = baos.toByteArray()
        var temp: String? = Base64.encodeToString(b, Base64.DEFAULT)
        return temp
    }
}