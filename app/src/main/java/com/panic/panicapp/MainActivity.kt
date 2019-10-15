package com.panic.panicapp

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_kebakaran.view.*
import kotlinx.android.synthetic.main.login_dialog.view.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)



            btnLogin.setOnClickListener{
                val username = edtUsername.text.toString()
                val password = edtPassword.text.toString()
                val intent = Intent(this, Beranda::class.java)

                if (username.isEmpty()){
                   edtUsername.error = "Email Harus Diisi"
                }
                if (password.isEmpty()){
                    edtPassword.error = "Password Harus Diisi"
                }


                if (password == "alvin19" && username == "alvinakbar095@gmail.com"){
                    startActivity(intent)

                }else if(password == "alvin19"  && username != "alvinakbar095@gmail.com"){

                    val mDialogView = LayoutInflater.from(this).inflate(R.layout.login_dialog, null)

                    val mBuilder = AlertDialog.Builder(this)
                        .setView(mDialogView)
                        .setTitle("         Email Salah       ")


                    val mAlertDialog = mBuilder.show()

                    mDialogView.btnRetry.setOnClickListener{
                        mAlertDialog.dismiss()
                    }
                }else if(password != "alvin19" && username == "alvinakbar095@gmail.com"){

                    val mDialogView = LayoutInflater.from(this).inflate(R.layout.login_dialog, null)

                    val mBuilder = AlertDialog.Builder(this)
                        .setView(mDialogView)
                        .setTitle("         Password Salah       ")


                    val mAlertDialog = mBuilder.show()

                    mDialogView.btnRetry.setOnClickListener{
                        mAlertDialog.dismiss()
                    }
                }else if(password != "alvin19" && username != "alvinakbar095@gmail.com"){

                    val mDialogView = LayoutInflater.from(this).inflate(R.layout.login_dialog, null)

                    val mBuilder = AlertDialog.Builder(this)
                        .setView(mDialogView)
                        .setTitle("  Password Salah dan Email Salah  ")


                    val mAlertDialog = mBuilder.show()

                    mDialogView.btnRetry.setOnClickListener{
                        mAlertDialog.dismiss()
                    }
                }






            }

            regText.setOnClickListener{
                val intent = Intent(this, RegisterActivity::class.java)
                startActivity(intent)
            }

    }



}


