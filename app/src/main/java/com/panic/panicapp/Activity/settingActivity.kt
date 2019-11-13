package com.panic.panicapp.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.panic.panicapp.R
import kotlinx.android.synthetic.main.setting_layout.*

class settingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.setting_layout)


        profilEdit.setOnClickListener {
            val intent = Intent(this, profilUpdate::class.java)
            startActivity(intent)
        }
        bantuanApp.setOnClickListener {
            Toast.makeText(this, "Bantuan Aplikasi", Toast.LENGTH_SHORT).show()
        }
        hubungiApp.setOnClickListener {
            Toast.makeText(this, "Hubungi Aplikasi", Toast.LENGTH_SHORT).show()
        }
        pengaturanApp.setOnClickListener {
            Toast.makeText(this, "Pengaturan Aplikasi", Toast.LENGTH_SHORT).show()
        }

        back_setting.setOnClickListener {
            onBackPressed()
        }

    }

}