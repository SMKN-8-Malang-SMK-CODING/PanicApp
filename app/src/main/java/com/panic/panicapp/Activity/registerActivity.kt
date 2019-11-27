package com.panic.panicapp.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.FirebaseAuthCredentialsProvider
import com.panic.panicapp.Databases.userNumber
import com.panic.panicapp.R
import kotlinx.android.synthetic.main.activity_register.*

class registerActivity : AppCompatActivity() {

    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        btnReg.setOnClickListener {

            val username = regUsername.text.toString()
            val email = regEmail.text.toString()
            val password = regPassword.text.toString()
            val user_phone = regHp.text.toString()

            auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
                Toast.makeText(this, "Berhasil Register ", Toast.LENGTH_SHORT).show()

                auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
                    val currentUser = auth.currentUser

                    val profileUpdate =
                        UserProfileChangeRequest.Builder()
                            .setDisplayName(username)
                            .build()

                    db.collection("user_phone").document(currentUser?.uid.toString()).set(
                        userNumber(
                            user_phone
                        )
                    )
                    currentUser?.updateProfile(profileUpdate)?.addOnSuccessListener {
                        Toast.makeText(this, "Hi! $username", Toast.LENGTH_SHORT).show()

                        val intent = Intent(this, berandaActivity::class.java)

                        intent.flags =
                            Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK

                        startActivity(intent)

                    }
                }
            }.addOnFailureListener {
                FirebaseException("Throwable")
                Toast.makeText(this, "${it.message}", Toast.LENGTH_SHORT).show()
            }

        }

    }


}
