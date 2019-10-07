package com.panic.panicapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_beranda.*

class Beranda : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beranda)

        vp_main.adapter = MyPagerAdapter(supportFragmentManager)
        tab_main.setupWithViewPager(vp_main)


    }
}
