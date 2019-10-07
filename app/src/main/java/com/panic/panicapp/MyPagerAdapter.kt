
package com.panic.panicapp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

@Suppress("DEPRECATION")
class MyPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {


    private val pages = listOf(
        MainFragment(),
        ProfilFragment()
    )

    override fun getItem(position: Int): Fragment {
        return pages[position]
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Beranda"
            else -> "News"
        }
    }
}