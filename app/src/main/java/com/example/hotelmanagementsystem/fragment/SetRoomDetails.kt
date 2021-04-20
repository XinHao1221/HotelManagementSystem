package com.example.hotelmanagementsystem.fragment

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
import com.example.hotelmanagementsystem.database.rooms.HotelRoom
import com.example.hotelmanagementsystem.database.rooms.HotelRoomViewModel
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
class SetRoomDetails : Fragment(), View.OnClickListener {

    private lateinit var mHotelRoomViewModel: HotelRoomViewModel

    private lateinit var roomSpinner: Spinner
    private lateinit var roomName: TextView
    private lateinit var editDescription: EditText
    private lateinit var editRoomName: EditText
    private lateinit var backButton: Button
    private lateinit var nextButton: Button

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_set_room_detail, container, false)

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
        var roomID: String? = arguments?.getString("RoomID")
        //初始化mHotelRoomViewModel
        mHotelRoomViewModel = ViewModelProvider(this).get(HotelRoomViewModel::class.java)
        //找到控件
        roomName = view.findViewById(R.id.RoomName)
        roomSpinner = view.findViewById(R.id.roomSpinner)
        editRoomName = view.findViewById(R.id.editRoomName)
        editDescription = view.findViewById(R.id.editDescription)
        backButton = view.findViewById(R.id.backButton)
        nextButton = view.findViewById(R.id.nextButton)

        var rDetails: HotelRoom? = null
        if (roomID != null) {
            rDetails = mHotelRoomViewModel.selectRoomDetails(roomID)
        }
        var roomType:Int = 1;
        when(rDetails?.roomType.toString()){
                "Single Room" -> roomType = 0
                "Double Room" -> roomType = 1
                "King Size Room" -> roomType = 2
                "Standard Room" -> roomType = 3
                "Triple Room" -> roomType = 4
                "Quad Room" -> roomType = 5
                "Deluxe Room" -> roomType = 6
        }
        roomName.text = rDetails?.roomName.toString()
        roomSpinner.setSelection(roomType)
        var roomName:String = rDetails?.roomName.toString()
        editRoomName.setText(roomName)
        var roomDescription:String = rDetails?.description.toString()
        editDescription.setText(roomDescription)

        backButton.setOnClickListener(this)
        nextButton.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        var updateOk = "Successfully update room details"
        val roomName = editRoomName.text.toString()
        val roomType = roomSpinner.selectedItem.toString()
        val roomDescription = editDescription.text.toString()
        var roomID: String? = arguments?.getString("RoomID")
        if (roomID != null&&inputCheck(roomName)) {
            mHotelRoomViewModel.updateRoomDetails(roomID,roomName,roomType,roomDescription)
            toastmsg(updateOk)
            if (v == backButton) {
                var rDetails: HotelRoom = mHotelRoomViewModel.selectRoomDetails(roomID)
                val bundle = bundleOf("floorID" to rDetails.floorID)
                findNavController().navigate(R.id.action_setRoomDetails_to_roomManagement, bundle)
            }else if(v==nextButton){
                val bundle = bundleOf("RoomID" to roomID)
                findNavController().navigate(R.id.action_setRoomDetails_to_setRoomPrice, bundle)
            }
        }
    }
    private fun inputCheck(roomName:String):Boolean{
        return !(TextUtils.isEmpty(roomName))
    }
    private fun toastmsg(msg: String){
        Toast.makeText(requireView().context, msg, Toast.LENGTH_SHORT).show();
    }
}