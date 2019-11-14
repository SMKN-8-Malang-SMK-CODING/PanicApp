package com.panic.panicapp.Activity

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.panic.panicapp.R
import kotlinx.android.synthetic.main.activity_profil_update.*
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
            load_update_profil.visibility = View.VISIBLE
            load_back_update_profil.visibility = View.VISIBLE


            val username = username_profil.text.toString()
            val phone = phone_number_profil.text.toString()

            if (username.isEmpty()) username_profil.error = "Harus Diisi"

            val profilUpdates = UserProfileChangeRequest.Builder()
                .setDisplayName(username)
                .build()

            currentUser?.updateProfile(profilUpdates)
                ?.addOnSuccessListener {
                    load_update_profil.visibility = View.GONE
                    load_back_update_profil.visibility = View.GONE
                    Toast.makeText(this, "Berhasil Update Profil", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, berandaActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }

        }

        chose_image.setOnClickListener {
            choseImage()
        }


    }


    private fun choseImage() {
        val intent = Intent(Intent.ACTION_PICK)
        val mimeType = arrayOf("image/jpeg", "image/png")

        intent.type = "image/*"
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
