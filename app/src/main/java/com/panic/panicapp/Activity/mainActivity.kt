package com.panic.panicapp.Activity

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.panic.panicapp.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.login_dialog.view.*


class mainActivity : AppCompatActivity() {

    private val auth = FirebaseAuth.getInstance()
    private var currentUser: FirebaseUser? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )


        currentUser = auth.currentUser
        val mDialogView = LayoutInflater.from(this).inflate(R.layout.login_dialog, null)

        val mBuilder = AlertDialog.Builder(this)

        if (currentUser != null) {
            moveToBeranda()
        } else {
            btnLogin.setOnClickListener {
                val username = edtUsername.text.toString()
                val password = edtPassword.text.toString()

                if (username.isEmpty()) {
                    edtUsername.error = "Email Harus Diisi"
                }
                if (password.isEmpty()) {
                    edtPassword.error = "Password Harus Diisi"
                }

                auth.signInWithEmailAndPassword(username, password).addOnCompleteListener {
                    if (!it.isSuccessful) {
                        mBuilder.setView(mDialogView)
                        mBuilder.setTitle("Gagal Login")

                        val mAlertDialog = mBuilder!!
                            .show()

                        mDialogView.btnRetry.setOnClickListener {
                            mAlertDialog.dismiss()
                        }
                    } else {
                        moveToBeranda()
                    }
                }.addOnFailureListener {
                    Toast.makeText(this, "Gagal Login ${it.message}", Toast.LENGTH_SHORT).show()

                    return@addOnFailureListener
                }
            }
        }


        regText.setOnClickListener {
            val intent = Intent(this, registerActivity::class.java)
            startActivity(intent)
        }

    }

    private fun moveToBeranda() {
        Toast.makeText(this, "Berhasil Login ${auth.currentUser?.uid}", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, berandaActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }


}


