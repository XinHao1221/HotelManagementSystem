package com.example.hotelmanagementsystem.lostfound

import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.net.Uri
import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hotelmanagementsystem.R
import com.example.hotelmanagementsystem.database.lostitems.LostItem
import com.example.hotelmanagementsystem.database.lostitems.LostFoundMenuHandler
import com.example.hotelmanagementsystem.database.lostitems.LostFoundMenuViewModel
import kotlinx.android.synthetic.main.fragment_edit_lost_item.view.*
import java.io.FileNotFoundException
import java.io.InputStream
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*


class EditLostItemFragment : Fragment() {
    private val args: EditLostItemFragmentArgs by navArgs()
    private val sharedViewModel: LostFoundMenuHandler by activityViewModels()
    private lateinit var lostFoundMenuViewModel: LostFoundMenuViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_edit_lost_item, container, false)

        (activity as AppCompatActivity?)!!.supportActionBar!!.title = "Edit Lost Item"

        lostFoundMenuViewModel = ViewModelProvider(this).get(LostFoundMenuViewModel::class.java)
        setArgsToSharedViewModel()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        var mYear: Int
        var mMonth: Int
        var mDay: Int
        var mHour: Int
        var mMinute: Int
        var date_time: String = ""

        fun timePicker() {
            // Get Current Time
            val c = Calendar.getInstance()
            mHour = c[Calendar.HOUR_OF_DAY]
            mMinute = c[Calendar.MINUTE]

            // Launch Time Picker Dialog
            val timePickerDialog = TimePickerDialog(
                this.requireContext(),
                OnTimeSetListener { _, hourOfDay, minute ->
                    mHour = hourOfDay
                    mMinute = minute
                    date_time = "$date_time $hourOfDay:$minute"
                    var cal = Calendar.getInstance()
                    val sdf = SimpleDateFormat("dd-MM-yyyy hh:mm")
                    cal.time = sdf.parse(date_time)
                    view.edit_found_time.setText(DateFormat.format("dd-MM-yyyy hh:mm:ss a", cal.timeInMillis))
                }, mHour, mMinute, false
            )
            timePickerDialog.show()
        }

        fun datePicker() {

            // Get Current Date
            val c = Calendar.getInstance()
            mYear = c[Calendar.YEAR]
            mMonth = c[Calendar.MONTH]
            mDay = c[Calendar.DAY_OF_MONTH]
            val datePickerDialog = DatePickerDialog(
                this.requireContext(),
                OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                    date_time = dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year
                    timePicker()
                }, mYear, mMonth, mDay
            )
            datePickerDialog.show()
        }

        super.onViewCreated(view, savedInstanceState)
        view.edit_lost_loc.setText(sharedViewModel.lostLocation)
        view.edit_lost_desc.setText(sharedViewModel.description)
        view.edit_found_status.isChecked = sharedViewModel.foundStatus
        if (sharedViewModel.foundTime != null) {
            view.edit_found_time.setText(
                DateFormat.format(
                    "dd-MM-yyyy hh:mm:ss a",
                    sharedViewModel.foundTime
                )
            )
        }
        view.edit_lost_item_img.setImageBitmap(sharedViewModel.itemImg)

        view.upload_img_button2.setOnClickListener{
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1)
        }

        view.remove_img_button2.setOnClickListener {
            view.edit_lost_item_img.setImageResource(R.drawable.no_image)
        }

        view.edit_found_time.setOnClickListener {
            datePicker()
        }

        if (!view.edit_found_status.isChecked) {
            view.edit_found_time.isClickable = false
        }

        view.edit_found_status.setOnCheckedChangeListener { compoundButton: CompoundButton, b: Boolean ->
            if (view.edit_found_status.isChecked) {
                val cal = Calendar.getInstance()
                view.edit_found_time.setText(DateFormat.format("dd-MM-yyyy hh:mm:ss a", cal.timeInMillis))
                view.edit_found_time.isClickable = true
            }
            else {
                view.edit_found_time.isClickable = false
                view.edit_found_time.setText("")
            }
        }

        view.edit_lost_item_button.setOnClickListener {
            val lostLoc = view.edit_lost_loc.text.toString()
            val desc = view.edit_lost_desc.text.toString()
            val image = (view.edit_lost_item_img.drawable as BitmapDrawable).bitmap
            val founded = view.edit_found_status.isChecked
            val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss a")
            var time: Date?
            time = if (view.edit_found_time.text.toString() == "") {
                null
            } else {
                val local = LocalDate.parse(view.edit_found_time.text.toString(), formatter)
                Date.from(local.atStartOfDay(ZoneId.systemDefault()).toInstant())
            }
            val lostItem = LostItem(sharedViewModel.lostItemId!!, image, lostLoc, desc, founded, time)
            lostFoundMenuViewModel.updateLostItem(lostItem)
            Toast.makeText(requireContext(), "Lost Item Edited", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_editLostItemFragment_to_lostfoundMenuFragment)
        }

        view.delete_lost_item_button.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setNegativeButton("No"){_, _ ->
            }
            // Confirm delete
            builder.setPositiveButton("Yes"){_, _->
                val lostItem = LostItem(sharedViewModel.lostItemId!!, sharedViewModel.itemImg!!, sharedViewModel.lostLocation,
                    sharedViewModel.description, sharedViewModel.foundStatus, sharedViewModel.foundTime)
                lostFoundMenuViewModel.deleteLostItem(lostItem)
                Toast.makeText(requireContext(), "Deleted Item", Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_editLostItemFragment_to_lostfoundMenuFragment)
            }
            builder.setTitle("Are you sure to delete this item from lost & found?");
            builder.setMessage("Confirm Delete?")
            builder.create().show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1) {
            super.onActivityResult(requestCode, resultCode, data)

            if (resultCode == Activity.RESULT_OK) {
                try {
                    val imageUri: Uri = data!!.data!!
                    val imageStream: InputStream = requireActivity().contentResolver.openInputStream(
                        imageUri
                    )!!
                    val selectedImage = BitmapFactory.decodeStream(imageStream)
                    requireView().edit_lost_item_img.setImageBitmap(selectedImage)
                } catch (e: FileNotFoundException) {
                    e.printStackTrace()
                    Toast.makeText(context, "Something went wrong", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(context, "You haven't picked Image", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun setArgsToSharedViewModel(){
        sharedViewModel.setLostItemId(args.currentLostItem.lostItemId)
        sharedViewModel.setLostLocation(args.currentLostItem.lostLocation)
        sharedViewModel.setDescription(args.currentLostItem.description)
        sharedViewModel.setItemImg(args.currentLostItem.itemImg)
        sharedViewModel.setFoundStatus(args.currentLostItem.foundStatus)
        sharedViewModel.setFoundTime(args.currentLostItem.foundTime)
    }
}