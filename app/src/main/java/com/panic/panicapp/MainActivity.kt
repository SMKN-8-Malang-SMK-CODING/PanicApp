package com.panic.panicapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import kotlinx.android.synthetic.main.activity_main.*




class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)



            btnLogin.setOnClickListener{
                val username = edtUsername.text
                val password = edtPassword.text
                val intent = Intent(this, Beranda::class.java)

                if (username.isEmpty()){
                   edtUsername.error = "Email Harus Diisi"
                }
                if (password.isEmpty()){
                    edtPassword.error = "Password Harus Diisi"
                }
                if (password.isNotEmpty() && username.isNotEmpty()){
                    startActivity(intent)
                }



            }

    }



}


