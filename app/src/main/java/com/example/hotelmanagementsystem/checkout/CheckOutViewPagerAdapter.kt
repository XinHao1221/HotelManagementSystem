package com.example.hotelmanagementsystem.checkout

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class CheckOutViewPagerAdapter(supportFragmentManager: FragmentManager): FragmentPagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val checkOutFragmentList = ArrayList<Fragment>()
    private val checkOutFragmentTitleList = ArrayList<String>()

    override fun getItem(position: Int): Fragment {
        return checkOutFragmentList[position]
    }

    override fun getCount(): Int {
        return checkOutFragmentList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return checkOutFragmentTitleList[position]
    }

    fun addFragment(fragment: Fragment, title:String){
        checkOutFragmentList.add(fragment)
        checkOutFragmentTitleList.add(title)
    }



}