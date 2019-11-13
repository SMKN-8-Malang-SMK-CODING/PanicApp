package com.panic.panicapp.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.panic.panicapp.Adapter.MyPagerAdapter
import com.panic.panicapp.R
import kotlinx.android.synthetic.main.activity_beranda.*

class berandaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beranda)
        val currentUser = FirebaseAuth.getInstance().currentUser

        vp_main.adapter = MyPagerAdapter(supportFragmentManager)
        tab_main.setupWithViewPager(vp_main)

        if (currentUser == null) {
            val intent = Intent(this, mainActivity::class.java)
            Toast.makeText(this, "User tidak ada", Toast.LENGTH_SHORT).show()
            startActivity(intent)
        }


    }
}
