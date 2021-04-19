package com.example.myapp.fragment

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.hotelmanagementsystem.R
import com.example.myapp.database.HotelRoom
import com.example.myapp.database.HotelRoomViewModel
import kotlinx.android.synthetic.main.fragment_room_management.*
import kotlinx.android.synthetic.main.fragment_set_room_detail.*
import kotlinx.android.synthetic.main.fragment_sign_up.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Fragment3.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddRoom1 : Fragment(), View.OnClickListener {

    private lateinit var mHotelRoomViewModel: HotelRoomViewModel

    private lateinit var roomSpinner: Spinner
    private lateinit var editDescription: EditText
    private lateinit var editRoomName: EditText
    private lateinit var editPriceWorkday: EditText
    private lateinit var editPriceWeekend: EditText
    private lateinit var editPriceHoliday: EditText
    private lateinit var saveButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_add_room1, container, false)

        val spinner: Spinner = root.findViewById(R.id.roomSpinner)
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.room_lunch,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter

            return root
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //接收传值
        //初始化mHotelRoomViewModel
        mHotelRoomViewModel = ViewModelProvider(this).get(HotelRoomViewModel::class.java)
        //找到控件
        roomSpinner = view.findViewById(R.id.roomSpinner)
        editRoomName = view.findViewById(R.id.editRoomName)
        editDescription = view.findViewById(R.id.editDescription)
        editPriceWorkday = view.findViewById(R.id.workingDayPrice)
        editPriceWeekend = view.findViewById(R.id.weekendPrice)
        editPriceHoliday = view.findViewById(R.id.holidayPrice)
        saveButton = view.findViewById(R.id.saveButton)

        saveButton.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        var floorID: String? = arguments?.getString("floorID")
        var addOk = "Successfully add room details"
        var addFail = "Has empty string"
        val roomType = roomSpinner.selectedItem.toString()
        val roomName = editRoomName.text.toString()
        val roomDescription = editDescription.text.toString()
        val priceWorkday = editPriceWorkday.text.toString()
        val priceWeekend = editPriceWeekend.text.toString()
        val priceHoliday = editPriceHoliday.text.toString()
        var id:Int = mHotelRoomViewModel.getRoomCount()
        var roomID:String?=null
        if(id<100) {
            roomID = "F" + String.format("%03d", id)
        }
        if (roomID != null&&inputCheck(priceWorkday,priceWeekend,priceHoliday)) {
            toastmsg(roomID)
        }
        if(inputCheck(roomName)&&inputCheck(priceWorkday,priceWeekend,priceHoliday)) {
            roomID?.let { HotelRoom(it,floorID,roomName,roomType,roomDescription,priceWorkday.toDouble(),priceWeekend.toDouble(),priceHoliday.toDouble()) }
                ?.let { mHotelRoomViewModel.addRoom(it) }
            toastmsg(addOk)
            val bundle = bundleOf("floorID" to floorID)
            findNavController().navigate(R.id.action_addRoom1_to_floorManagement, bundle)
        }else{
            toastmsg(addFail)
        }
    }
    private fun inputCheck(roomName:String):Boolean{
        return !(TextUtils.isEmpty(roomName))
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