package com.panic.panicapp.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.panic.panicapp.Fragment.mainFragment
import com.panic.panicapp.Fragment.newsFragment


@Suppress("DEPRECATION")
class MyPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val pages = listOf(
        mainFragment(),
        newsFragment()
    )

    override fun getItem(position: Int): Fragment {
        return pages[position]
    }

    override fun getCount(): Int {
        return pages.size
    }


    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> ""
            else -> ""
        }
    }
}