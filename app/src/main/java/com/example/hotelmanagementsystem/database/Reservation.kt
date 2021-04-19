package com.example.hotelmanagementsystem.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*
//tableName = "Reservation"

// To make this object able to pass to another fragment
@Parcelize
@Entity(tableName = "reservation_table")
data class  Reservation(

    @PrimaryKey(autoGenerate = true)
    val reservationId: Int,

    @ColumnInfo(name = "guest_name")
    val guestName: String,

    @ColumnInfo(name = "id_type")
    val idType:String,

    @ColumnInfo(name = "id_no")
    val idNo:String,

    @ColumnInfo(name = "room_type")
    val roomType:String,

    @ColumnInfo(name = "check_in_date")
    val checkInDate:String?,

    @ColumnInfo(name = "check_out_date")
    val checkOutDate: String?,

    @ColumnInfo(name = "guest_email")
    val guestEmail: String?,

    @ColumnInfo(name = "guest_phone_no")
    val guestPhoneNo: String?,

    @ColumnInfo(name = "payment_method")
    var paymentMethod : String,

    @ColumnInfo(name = "referemce_no")
    var referenceNo:String,

    @ColumnInfo(name = "status")
    var status:String,

    @ColumnInfo(name = "room_id")
    var roomId : String



): Parcelable
