package com.example.hotelmanagementsystem.fragment

import android.os.Bundle
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
import com.example.hotelmanagementsystem.database.staffs.Staff
import com.example.hotelmanagementsystem.database.staffs.StaffViewModel


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Login.newInstance] factory method to
 * create an instance of this fragment.
 */
class Login : Fragment(), View.OnClickListener {

    private lateinit var mStaffViewModel: StaffViewModel
    // TODO: Rename and change types of parameters
    private lateinit var loginButtonControl: Button
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //找到控件
        mStaffViewModel = ViewModelProvider(this).get(StaffViewModel::class.java)

        usernameEditText = view.findViewById(R.id.editTextUserName)
        passwordEditText = view.findViewById(R.id.editTextPassword)
        loginButtonControl = view.findViewById(R.id.loginButton)


        loginButtonControl.setOnClickListener (this)
    }
    override fun onClick(v: View?) {
        var username:String? = usernameEditText.text.toString()
        var password:String? = passwordEditText.text.toString()

        var loginOk = "Successfully logged in"
        var fail:Boolean = true
        var loginFail = "Incorrect username or password"

        var sList: List<Staff> = mStaffViewModel.readAllStaff

        for(i in sList.indices){
            if (username.equals(sList.get(i).name)){
                if(password.equals(sList.get(i).password)) {
                    toastmsg(loginOk)
                    fail=false
                    findNavController().navigate(R.id.action_login_to_floorManagement)
                }
            }
        }
        if(fail==true) {
            toastmsg(loginFail)
        }

    }
    private fun toastmsg(msg: String){
        Toast.makeText(requireView().context, msg, Toast.LENGTH_SHORT).show();
    }

}