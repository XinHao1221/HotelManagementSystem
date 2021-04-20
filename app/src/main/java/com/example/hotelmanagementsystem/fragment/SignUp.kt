package com.example.hotelmanagementsystem.fragment

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.hotelmanagementsystem.R
import com.example.hotelmanagementsystem.database.staffs.StaffViewModel
import kotlinx.android.synthetic.main.fragment_sign_up.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SignUp.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignUp : Fragment(), View.OnClickListener {

    private lateinit var mStaffViewModel: StaffViewModel

    private lateinit var signUpButtonControl: Button
    private lateinit var usernameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var confirmPasswordEditText: EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //找到控件
        mStaffViewModel = ViewModelProvider(this).get(StaffViewModel::class.java)

        usernameEditText = view.findViewById(R.id.editTextName)
        emailEditText = view.findViewById(R.id.editTextEmailAddress)
        passwordEditText = view.findViewById(R.id.editTextPassword)
        confirmPasswordEditText = view.findViewById(R.id.editTextConfirmPassword)
        signUpButtonControl = view.findViewById(R.id.signUpButton)
        signUpButtonControl.setOnClickListener (this)
    }

    override fun onClick(v: View?) {
        insertStaffData()
    }

    private fun insertStaffData() {
        var signUpOk = "Successfully Sign Up Account"
        var passwordFail = "Passwords are not the same"
        val name = editTextName.text.toString()
        val email = editTextEmailAddress.text.toString()
        val password = editTextPassword.text.toString()
        val confirmPassword = editTextConfirmPassword.text.toString()
        if(inputCheck(name,email,password,confirmPassword)){
            if(password.equals(confirmPassword)){
                mStaffViewModel.signUpStaff("S1001",name,email,password)
                toastmsg(signUpOk)
                findNavController().navigate(R.id.action_signUp_to_easeHotel)
            }else{
                toastmsg(passwordFail)
            }
        }
    }

    private fun inputCheck(name: String,email:String,password:String,confirmPassword:String):Boolean{
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(email) && TextUtils.isEmpty(password) && TextUtils.isEmpty(confirmPassword))
    }

    private fun toastmsg(msg: String){
        Toast.makeText(requireView().context, msg, Toast.LENGTH_SHORT).show();
    }

}