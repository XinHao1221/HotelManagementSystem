package com.example.hotelmanagementsystem.lostfound

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.hotelmanagementsystem.R
import com.example.hotelmanagementsystem.database.lostitems.LostItem
import com.example.hotelmanagementsystem.database.lostitems.LostFoundMenuViewModel
import kotlinx.android.synthetic.main.fragment_add_lost_item.*
import kotlinx.android.synthetic.main.fragment_add_lost_item.view.*
import java.io.FileNotFoundException
import java.io.InputStream


class AddLostItemFragment : Fragment() {

    private lateinit var lostFoundMenuViewModel: LostFoundMenuViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_add_lost_item, container, false)

        (activity as AppCompatActivity?)!!.supportActionBar!!.title = "Add Lost Item"

        lostFoundMenuViewModel = ViewModelProvider(this).get(LostFoundMenuViewModel::class.java)

        view.upload_img_button.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1)
        }

        view.remove_img_button.setOnClickListener {
            add_lost_item_img.setImageResource(R.drawable.no_image)
        }

        view.add_lost_item_button.setOnClickListener {
            var lostLoc = add_lost_loc.text.toString()
            var desc = add_lost_desc.text.toString()
            var image = (add_lost_item_img.drawable as BitmapDrawable).bitmap
            var lostItem = LostItem(0.toLong(), image, lostLoc, desc, false, null)
            lostFoundMenuViewModel.insertLostItem(lostItem)
            Toast.makeText(requireContext(), "Lost Item Added", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addLostItemFragment_to_lostfoundMenuFragment)
        }

        return view
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
                    add_lost_item_img.setImageBitmap(selectedImage)
                } catch (e: FileNotFoundException) {
                    e.printStackTrace()
                    Toast.makeText(context, "Something went wrong", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(context, "You haven't picked Image", Toast.LENGTH_LONG).show()
            }
        }
    }
}