package com.panic.panicapp

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.setting_layout.*

class settingActivity  : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.setting_layout)


        profilEdit!!.setOnClickListener{
            Toast.makeText(this, "Edit profile", Toast.LENGTH_SHORT).show()
        }
        bantuanApp.setOnClickListener{
            Toast.makeText(this, "Bantuan Aplikasi", Toast.LENGTH_SHORT).show()
        }
        hubungiApp.setOnClickListener{
            Toast.makeText(this, "Hubungi Aplikasi", Toast.LENGTH_SHORT).show()
        }
        pengaturanApp.setOnClickListener{
            Toast.makeText(this, "Pengaturan Aplikasi", Toast.LENGTH_SHORT).show()
        }

        back_setting.setOnClickListener {
            onBackPressed()
        }

    }

}