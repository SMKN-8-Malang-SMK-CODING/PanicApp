package com.panic.panicapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        btnReg.setOnClickListener{

            val username = regUsername.text.toString()
            val email = regEmail.text.toString()
            val password = regPassword.text.toString()

            auth.createUserWithEmailAndPassword(email,password).addOnSuccessListener {
                Toast.makeText(this,"Berhasil Register ", Toast.LENGTH_SHORT).show()

                auth.signInWithEmailAndPassword(email,password).addOnSuccessListener {
                    val currentUser = auth.currentUser

                    val profileUpdate = UserProfileChangeRequest.Builder().setDisplayName(username).build()

                    currentUser?.updateProfile(profileUpdate)?.addOnSuccessListener {
                        Toast.makeText(this, "Hi! $username", Toast.LENGTH_SHORT).show()

                        val intent = Intent(this, Beranda::class.java)

                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK

                        startActivity(intent)

                    }
                }
            }.addOnFailureListener{
                Toast.makeText(this, "${it.message}", Toast.LENGTH_SHORT).show()
            }

        }

    }


}
