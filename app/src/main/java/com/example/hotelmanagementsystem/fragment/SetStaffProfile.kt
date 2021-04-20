package com.example.hotelmanagementsystem.fragment

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.hotelmanagementsystem.R
import com.example.hotelmanagementsystem.database.staffs.Staff
import com.example.hotelmanagementsystem.database.staffs.StaffViewModel

class SetStaffProfile : Fragment(),View.OnClickListener {

    private lateinit var rStaffViewModel: StaffViewModel

    private lateinit var textStaffID: TextView
    private lateinit var editStaffName: EditText
    private lateinit var staffSpinner: Spinner
    private lateinit var editEmail: EditText
    private lateinit var editPhone: EditText
    private lateinit var textBirthday: TextView
    private lateinit var editWage: EditText
    private lateinit var swhStatusAdmin: Switch
    private lateinit var swhStatusAcc: Switch
    private lateinit var saveButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_staff_profile, container, false)

        val spinner: Spinner = root.findViewById(R.id.staffSpinner)
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.staff_lunch,
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
        val staffID: String? = arguments?.getString("staffID")
        rStaffViewModel = ViewModelProvider(this).get(StaffViewModel::class.java)

        textStaffID = view.findViewById(R.id.textStaffID)
        editStaffName=view.findViewById(R.id.editStaffName)
        staffSpinner=view.findViewById(R.id.staffSpinner)
        editEmail=view.findViewById(R.id.editEmail)
        editPhone=view.findViewById(R.id.editPhone)
        textBirthday=view.findViewById(R.id.textBrithday)
        editWage=view.findViewById(R.id.editWage)
        swhStatusAdmin=view.findViewById(R.id.swh_status_admin)
        swhStatusAcc=view.findViewById(R.id.swh_status_acc)
        saveButton=view.findViewById(R.id.saveButton)
        var sProfile: Staff? = null
        if(staffID!=null) {
            sProfile = rStaffViewModel.readStaffProfile(staffID)
        }
        textStaffID.text = sProfile?.staffID
        editStaffName.setText(sProfile?.name.toString())
        when(sProfile?.identity.toString()) {
            "Manager" -> staffSpinner.setSelection(0)
            "Staff" -> staffSpinner.setSelection(1)
        }
        editEmail.setText(sProfile?.emailAddress.toString())
        editPhone.setText(sProfile?.phoneNumber.toString())
        textBirthday.text = sProfile?.birthday.toString()
        editWage.setText(sProfile?.wage.toString())
        sProfile?.admin?.let { swhStatusAdmin.setChecked(it) }
        sProfile?.activateAcc?.let { swhStatusAcc.setChecked(it) }

        saveButton.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        val updateOk = "Successfully update staff profile"
        val staffName = editStaffName.text.toString()
        val identity = staffSpinner.selectedItem.toString()
        val email = editEmail.text.toString()
        val phoneNo = editPhone.text.toString()
        val birthday = textBirthday.text.toString()
        val wage = editWage.text.toString()
        val admin = swhStatusAdmin.isChecked
        val acc = swhStatusAcc.isChecked
        val staffID: String? = arguments?.getString("staffID")

        if (staffID != null&& inputCheck(staffName,identity,email,phoneNo,birthday,wage)) {
            rStaffViewModel.updateStaffDetails(staffID,staffName,identity,email,phoneNo,birthday,wage.toDouble(),admin,acc)
            toastmsg(updateOk)
            findNavController().navigate(R.id.action_setStaffProfile_to_staffManagement)
        }
    }
    private fun inputCheck(staffName:String,identity:String,email:String,phone:String,birthday:String,wage:String):Boolean{
        return !(TextUtils.isEmpty(staffName)||TextUtils.isEmpty(identity)||TextUtils.isEmpty(email)||
                TextUtils.isEmpty(phone)||TextUtils.isEmpty(birthday)|| TextUtils.isEmpty(wage)||TextUtils.isDigitsOnly(wage))
    }
    private fun toastmsg(msg: String){
        Toast.makeText(requireView().context, msg, Toast.LENGTH_SHORT).show();
    }
}
