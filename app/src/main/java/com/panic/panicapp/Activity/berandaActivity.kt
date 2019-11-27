package com.panic.panicapp.Activity


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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

        tab_main.getTabAt(0)!!.setIcon(R.drawable.home_icon)
        tab_main.getTabAt(1)!!.setIcon(R.drawable.laporan_icon)


    }
}
