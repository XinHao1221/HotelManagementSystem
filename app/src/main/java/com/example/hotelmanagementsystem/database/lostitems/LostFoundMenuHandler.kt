package com.example.hotelmanagementsystem.database.lostitems

import android.graphics.Bitmap
import android.icu.text.DateFormat
import androidx.lifecycle.ViewModel
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

class LostFoundMenuHandler: ViewModel() {
    private var _lostItemId: Long? = null
    val lostItemId: Long?
        get()=_lostItemId

    private var _itemImg: Bitmap? = null
    val itemImg: Bitmap?
        get()=_itemImg

    private var _lostLocation = ""
    val lostLocation: String
        get()=_lostLocation

    private var _description = ""
    val description: String
        get()=_description

    private var _foundStatus: Boolean = false
    val foundStatus:Boolean
        get()=_foundStatus

    private var _foundTime: Date? = null
    val foundTime: Date?
        get()=_foundTime

    fun setLostItemId(id: Long?) {
        _lostItemId = id
    }

    fun setItemImg(img: Bitmap?) {
        _itemImg = img
    }

    fun setLostLocation(location: String) {
        _lostLocation = location
    }

    fun setDescription(desc: String) {
        _description = desc
    }

    fun setFoundStatus(status: Boolean) {
        _foundStatus = status
    }

    fun setFoundTime(time: Date?) {
        _foundTime = time
    }

    fun setFoundTime(timeStr: String) {
        _foundTime = if (timeStr == "") {
            null
        } else {
            var dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a")
            var ldt: LocalDateTime = LocalDateTime.parse(timeStr, dateTimeFormatter)
            Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant())
        }
    }
}