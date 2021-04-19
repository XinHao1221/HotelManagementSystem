package com.example.myapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.hotelmanagementsystem.R
import com.example.myapp.database.Staff
import com.example.myapp.database.StaffViewModel

class SetStaffProfile : Fragment(),View.OnClickListener {

    private lateinit var rStaffViewModel: StaffViewModel

    private lateinit var textStaffID: TextView
    private lateinit var editStaffName: EditText
    private lateinit var staffSpinner: Spinner
    private lateinit var editEmail: EditText
    private lateinit var editPhone: EditText
    private lateinit var textBirthday: TextView
    private lateinit var dateButton: ImageButton
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
        dateButton=view.findViewById(R.id.dateButton)
        editWage=view.findViewById(R.id.editWage)
        swhStatusAdmin=view.findViewById(R.id.swh_status_admin)
        swhStatusAcc=view.findViewById(R.id.swh_status_acc)
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

        //dateButton
        //dateButton
        //saveButton.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        val updateOk = "Successfully update room prices"
        val staffName = editStaffName.text.toString()
        val email = editEmail.text.toString()
        val phoneNo = editPhone.text.toString()
        val staffID: String? = arguments?.getString("staffID")
    }
}