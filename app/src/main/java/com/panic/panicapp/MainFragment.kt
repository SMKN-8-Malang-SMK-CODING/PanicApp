package com.panic.panicapp

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.afollestad.materialdialogs.bottomsheets.BottomSheet
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.dialog_kebakaran.view.*
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.laporan_dialog.view.*



/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback
    val db = FirebaseFirestore.getInstance()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val currentUser = FirebaseAuth.getInstance().currentUser
        user_name.text = currentUser?.displayName.toString()



        logoutTab.setOnClickListener {
            if (currentUser != null){
                FirebaseAuth.getInstance().signOut()
                val intent = Intent(context, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                Toast.makeText(context, "Berhasil Logout", Toast.LENGTH_SHORT).show()
                startActivity(intent)
                activity?.finish()

            }
        }
        kebakaranTab.setOnClickListener {
            alertLaporan("Kebakaran")
        }
        kecelakaanTab.setOnClickListener {
           alertLaporan("Kecelakaan")
        }
        kriminalTab.setOnClickListener {
            alertLaporan("Kriminalitas")

        }
        foto_profil.setOnClickListener{
            val intent = Intent(context, settingActivity::class.java)
            startActivity(intent)
        }
    }

    private fun alertLaporan( judul:String){
        val mDialogView = LayoutInflater.from(context).inflate(R.layout.dialog_kebakaran, null)

        val mBuilder = AlertDialog.Builder(this.context)
            .setView(mDialogView)
            .setTitle(judul)


        val mAlertDialog = mBuilder.show()

        mDialogView.btnSubmitKebakaran.setOnClickListener{
            val username = user_name.text.toString()
            val lokasi = lokasi_user.text.toString()
            mAlertDialog.dismiss()
            db.collection("laporan").add(addLaporan(username,lokasi, judul))
                .addOnSuccessListener {
                    val dialogBuild = LayoutInflater.from(context).inflate(R.layout.laporan_dialog, null)
                    val build = AlertDialog.Builder(this.context)
                        .setView(dialogBuild)

                    val alertdialogshow = build.show()

                    dialogBuild.sukses_btn.setOnClickListener{
                        alertdialogshow.dismiss()
                    }
                }.addOnFailureListener{
                    Toast.makeText(context, "${it.message}", Toast.LENGTH_SHORT).show()
                }
        }
        mDialogView.btnCancelKebakaran.setOnClickListener{
            mAlertDialog.cancel()
        }
    }
}