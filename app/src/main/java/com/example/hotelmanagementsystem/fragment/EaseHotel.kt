package com.example.hotelmanagementsystem.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.hotelmanagementsystem.R


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Use the [EaseHotel.newInstance] factory method to
 * create an instance of this fragment.
 */
class EaseHotel : Fragment() {
    private lateinit var loginButtonControl: Button
    private lateinit var signUpButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ease_hotel, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Inflate the layout for this fragment
        loginButtonControl = view.findViewById(R.id.loginButton)
        loginButtonControl.setOnClickListener {
            findNavController().navigate(R.id.action_easeHotel_to_login)
        }
        signUpButton = view.findViewById(R.id.signUpButton)
        signUpButton.setOnClickListener {
            findNavController().navigate(R.id.action_easeHotel_to_signUp)
        }
    }

}