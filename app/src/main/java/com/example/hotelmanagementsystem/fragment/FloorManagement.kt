package com.example.hotelmanagementsystem.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hotelmanagementsystem.R
import com.example.hotelmanagementsystem.database.floors.Floor
import com.example.hotelmanagementsystem.database.floors.FloorViewModel
import com.example.hotelmanagementsystem.fragment.list.FloorListAdapter
import kotlinx.android.synthetic.main.fragment_floor_management.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Fragment1.newInstance] factory method to
 * create an instance of this fragment.
 */
class FloorManagement : Fragment(), View.OnClickListener  {
    private lateinit var mFloorViewModel: FloorViewModel

    private lateinit var addButton: View


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_floor_management, container, false)

        setHasOptionsMenu(true)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Recyclerview
        val adapter= FloorListAdapter()
        val recyclerView = recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager=LinearLayoutManager(requireContext())
        //找到控件
        mFloorViewModel = ViewModelProvider(this).get(FloorViewModel::class.java)
        mFloorViewModel.getRoomAndFloor.observe(viewLifecycleOwner, Observer { floor ->
            adapter.setData(floor)
        })
        addButton=view.findViewById(R.id.fab)
        addButton.setOnClickListener(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deleteAllFloor()
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onClick(v: View?) {
        var id:Int = mFloorViewModel.getFloorCount() + 1
        var floorID:String?=null
        var floorName:String??=null
        if(id<25) {
            floorID = "F" + String.format("%03d", id)
            floorName  = id.toString() + "F"
        }
        floorID?.let { Floor(it, floorName) }?.let { mFloorViewModel.addFloor(it) }
    }

    private fun toastmsg(msg: String){
        Toast.makeText(requireView().context, msg, Toast.LENGTH_SHORT).show();
    }
    private fun deleteAllFloor(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){ _, _ ->
            mFloorViewModel.deleteAllFloor()
            Toast.makeText(
                requireContext(),
                "Successfully removed everything",
                Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("No"){_,_-> }
        builder.setTitle("Delete eyeryThing?")
        builder.setTitle("Are you sure you want to delete everyThing?")
        builder.create().show()
    }
}