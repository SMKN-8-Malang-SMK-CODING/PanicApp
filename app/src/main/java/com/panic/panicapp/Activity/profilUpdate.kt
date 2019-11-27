package com.panic.panicapp.Activity

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.panic.panicapp.Databases.phoneUpdate
import com.panic.panicapp.Databases.userNumber
import com.panic.panicapp.R
import kotlinx.android.synthetic.main.activity_profil_update.*
import java.io.File
import java.io.IOException


class profilUpdate : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()
    private val currentUser = auth.currentUser
    private val PICK_IMAGE_REQUEST = 71
    private val PERMISSION_CODE = 1001;
    private var filePath: Uri? = null
    private val Storage = FirebaseStorage.getInstance().reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil_update)

        btn_submit_updates.setOnClickListener {
            updateProfile()
        }

        chose_image.setOnClickListener {
            choseImage()
        }

        back_btn_update.setOnClickListener {
            onBackPressed()
        }

        Glide.with(this).load(currentUser?.photoUrl).apply(RequestOptions()).into(image_profil)
    }

    private fun updateProfile() {
        load_update_profil.visibility = View.VISIBLE
        load_back_update_profil.visibility = View.VISIBLE

        val ref: StorageReference? = Storage.child("user_photo/${currentUser?.uid}.jpg")

        ref!!.putFile(filePath!!).addOnSuccessListener {
            ref.downloadUrl.addOnSuccessListener {
                val phoneUser = db.collection("user_phone").document(currentUser?.uid.toString())
                val username = username_profil.text.toString()
                val phonenumber = phone_number_profil.text.toString()

                if (username.isEmpty() && phonenumber.isEmpty()) {
                    username_profil.error = "Harus Diisi"
                    phone_number_profil.error = "Harus Diisi"
                }

                phoneUser.set(
                    phoneUpdate(
                        phonenumber
                    )
                )

                val profilUpdates = UserProfileChangeRequest.Builder()
                    .setDisplayName(username)
                    .setPhotoUri(it)
                    .build()

                currentUser?.updateProfile(profilUpdates)
                    ?.addOnSuccessListener {
                        load_update_profil.visibility = View.GONE
                        load_back_update_profil.visibility = View.GONE
                        Toast.makeText(this, "Berhasil Update Profil", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, berandaActivity::class.java)
                        intent.flags =
                            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                    }?.addOnFailureListener {
                        Toast.makeText(this, "${it.message}", Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }

    private fun choseImage() {
        val intent = Intent()

        intent.type = "image/*"

        val mimeTypes = arrayOf("image/jpeg", "image/png")

        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
        intent.action = Intent.ACTION_GET_CONTENT

        startActivityForResult(Intent.createChooser(intent, "Plih Foto"), PICK_IMAGE_REQUEST)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            filePath = data.data

            try {
                Glide.with(this).load(filePath).into(image_profil)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

    }
}
