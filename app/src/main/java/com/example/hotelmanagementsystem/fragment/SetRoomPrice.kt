package com.example.myapp.fragment

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.hotelmanagementsystem.R
import com.example.myapp.database.HotelRoom
import com.example.myapp.database.HotelRoomViewModel

class SetRoomPrice : Fragment(),View.OnClickListener {

    private lateinit var mHotelRoomViewModel: HotelRoomViewModel

    private lateinit var editPriceWorkday: EditText
    private lateinit var editPriceWeekend: EditText
    private lateinit var editPriceHoliday: EditText
    private lateinit var saveButton: Button
    //private var builderForCustom: CustomDialog.Builder? = null
    //private var mDialog: CustomDialog? = null


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_set_room_price, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //接收传值
        var roomID: String? = arguments?.getString("RoomID")
        //初始化mHotelRoomViewModel
        mHotelRoomViewModel = ViewModelProvider(this).get(HotelRoomViewModel::class.java)
        //找到控件
        editPriceWorkday = view.findViewById(R.id.workingDayPrice)
        editPriceWeekend = view.findViewById(R.id.weekendPrice)
        editPriceHoliday = view.findViewById(R.id.holidayPrice)
        saveButton = view.findViewById(R.id.saveButton)

        var rDetails: HotelRoom? = null
        if (roomID != null) {
            rDetails = mHotelRoomViewModel.selectRoomDetails(roomID)
        }
        val priceWorkday:String = String.format("%.2f",(rDetails?.priceWorkday))
        editPriceWorkday.setText(priceWorkday)
        val priceWeekend:String = String.format("%.2f",(rDetails?.priceWeekend))
        editPriceWeekend.setText(priceWeekend)
        val priceHoliday:String =String.format("%.2f",(rDetails?.priceHoliday))
        editPriceHoliday.setText(priceHoliday)

        //builderForCustom = CustomDialog.Builder(this)

        saveButton.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        val updateOk = "Successfully update room prices"
        val priceWorkday = editPriceWorkday.text.toString()
        val priceWeekend = editPriceWeekend.text.toString()
        val priceHoliday = editPriceHoliday.text.toString()
        val roomID: String? = arguments?.getString("RoomID")
        if (roomID != null&&inputCheck(priceWorkday,priceWeekend,priceHoliday)) {
            mHotelRoomViewModel.updateRoomPrices(roomID, priceWorkday.toDouble(),priceWeekend.toDouble(),priceHoliday.toDouble())
            toastmsg(updateOk)
            val rDetails: HotelRoom = mHotelRoomViewModel.selectRoomDetails(roomID)
            val bundle = bundleOf("floorID" to rDetails.floorID)
            findNavController().navigate(R.id.action_setRoomPrice_to_roomManagement, bundle)
        }
    }
    private fun inputCheck(priceWorkday:String,priceWeekend:String,priceHoliday:String):Boolean{
        var check:Boolean = true
        if(TextUtils.isEmpty(priceWorkday)||TextUtils.isDigitsOnly(priceWorkday)){
            check = false
        }
        if(TextUtils.isEmpty(priceWeekend)||TextUtils.isDigitsOnly(priceWeekend)){
            check = false
        }
        if(TextUtils.isEmpty(priceHoliday)||TextUtils.isDigitsOnly(priceHoliday)){
            check = false
        }
        return check
    }

    private fun toastmsg(msg: String){
        Toast.makeText(requireView().context, msg, Toast.LENGTH_SHORT).show();
    }
}
