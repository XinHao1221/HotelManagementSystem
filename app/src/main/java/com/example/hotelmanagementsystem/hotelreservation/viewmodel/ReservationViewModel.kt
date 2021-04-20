package com.example.hotelmanagementsystem.hotelreservation.viewmodel

import androidx.lifecycle.ViewModel

class ReservationViewModel() :ViewModel() {

    // Neccessary data for Reservation
    // Guest Name setter and getter
    private var _guestName = ""
    val guestName:String
    get()=_guestName

    private var _idType = ""
    val idType:String
        get()=_idType

    private var _IDNo = ""
    val IDNo:String
        get()=_IDNo

    private var _roomType = ""
    val roomType:String
        get()=_roomType

    private var _checkInDate = ""
    val checkInDate:String
        get()=_checkInDate

    private var _checkOutDate = ""
    val checkOutDate:String
        get()=_checkOutDate

    private var _email = ""
    val email:String
        get()=_email

    private var _phoneNo = ""
    val phoneNo:String
        get()=_phoneNo

    private var _totalAmount = ""
    val totalAmount:String
        get()=_totalAmount

    private var _paymentMethod = ""
    val paymentMethod:String
        get()=_paymentMethod

    private var _referenceNo = ""
    val referenceNo:String
        get()=_referenceNo

    private var _status = ""
    val status:String
        get()=_status

    private var _roomID = ""
    val roomID:String
        get()=_roomID

    private var _reservationID = ""
    val reservationID:String
        get() = _reservationID

//    var selectedPosition = 0


    // Setter for each variable
    fun setGuestName(guestName: String){
        _guestName= guestName
    }

    fun setIdType(idType: String){
        _idType = idType
    }

    fun setIDNo(IDNo: String){
        _IDNo = IDNo
    }

    fun setRoomType(roomType: String){
        _roomType = roomType
    }

    fun setCheckInDate(checkInDate: String){
        _checkInDate = checkInDate
    }

    fun setCheckOutDate(checkOutDate: String){
        _checkOutDate = checkOutDate
    }

    fun setEmail(email: String){
        _email = email
    }

    fun setPhoneNo(phoneNo: String){
        _phoneNo = phoneNo
    }

    fun setTotalAmount(totalAmount: String){
        _totalAmount = totalAmount
    }

    fun setPaymentMethod(paymentMethod: String){
        _paymentMethod = paymentMethod
    }

    fun setReferenceNo(referenceNo: String){
        _referenceNo = referenceNo
    }

    fun setStatus(status: String){
        _status = status
    }

    fun setRoomID(roomID:String){
        _roomID = roomID
    }

    fun setReservationID(reservationID:String){
        _reservationID = reservationID
    }


}