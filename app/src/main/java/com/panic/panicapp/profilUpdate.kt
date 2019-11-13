package com.panic.panicapp

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.android.synthetic.main.activity_profil_update.*
import kotlinx.android.synthetic.main.fragment_main.*
import java.io.IOException

class profilUpdate : AppCompatActivity() {

    private val auth = FirebaseAuth.getInstance()
    private val currentUser = FirebaseAuth.getInstance().currentUser
    private val PICK_IMAGE_REQUEST = 71
    private var filePath: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil_update)

        btn_submit_updates.setOnClickListener {
            val username = username_profil.text.toString()

            if(username.isEmpty()) username_profil.error = "Harus Diisi"

            val profilUpdates = UserProfileChangeRequest.Builder()
                .setDisplayName(username)
                .build()
//        currentUser!!.updatePhoneNumber(phone)
//            .addOnSuccessListener {
//            }

            currentUser?.updateProfile(profilUpdates)
                ?.addOnSuccessListener {
                    Toast.makeText(this, "Berhasil Update Profil", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, Beranda::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }

        }

        image_profil.setOnClickListener {
            choseImage()
        }
    }


    private fun choseImage() {
        val intent = Intent(Intent.ACTION_PICK)
        val mimeType = arrayOf("image/jpeg", "image/png")

        intent.type = "image/"
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeType)

        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && requestCode == Activity.RESULT_OK && data != null && data.data != null) {
            filePath = data.data

            try {
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, filePath)
                image_profil.setImageBitmap(bitmap)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

    }
}
