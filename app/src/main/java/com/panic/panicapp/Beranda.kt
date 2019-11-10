package com.panic.panicapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_beranda.*

class Beranda : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beranda)
        val currentUser = FirebaseAuth.getInstance().currentUser

        vp_main.adapter = MyPagerAdapter(supportFragmentManager)
        tab_main.setupWithViewPager(vp_main)

        if(currentUser == null){
            val intent = Intent(this, MainActivity::class.java)
            Toast.makeText(this, "User tidak ada", Toast.LENGTH_SHORT).show()
            startActivity(intent)
        }


    }
}
