package com.panic.panicapp.Fragment

import android.animation.ObjectAnimator
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.common.collect.ComparisonChain.start
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.panic.panicapp.Activity.mainActivity
import com.panic.panicapp.Activity.profilUpdate
import com.panic.panicapp.Activity.settingActivity
import com.panic.panicapp.Databases.addLaporan
import com.panic.panicapp.R
import kotlinx.android.synthetic.main.dialog_kebakaran.*
import kotlinx.android.synthetic.main.dialog_kebakaran.view.*
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.laporan_dialog.view.*


/**
 * A simple [Fragment] subclass.
 */
class mainFragment : Fragment() {

    private val currentUser = FirebaseAuth.getInstance().currentUser
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    val db = FirebaseFirestore.getInstance()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        foto_profil.setOnClickListener {

            side_bar_menu.animate().apply {
                duration = 2000
                x(0f)
                y(0f)
                alpha(1f)
                start()
            }

            side_bar_menu.visibility = View.VISIBLE

            close_side_menu.setOnClickListener {
                side_bar_menu.visibility = View.GONE

                side_bar_menu.animate().apply {
                    duration = 2000
                    x(-100f)
                    y(0f)
                    alpha(0.6f)
                    start()
                }

            }

            menu_utama.setOnClickListener {
                side_bar_menu.visibility = View.GONE
            }

            edt_profil.setOnClickListener {
                side_bar_menu.visibility = View.GONE
                side_bar_menu.animate().apply {
                    duration = 2000
                    x(-100f)
                    y(0f)
                    alpha(0.6f)
                    start()
                }


                val intent = Intent(context, profilUpdate::class.java)
                startActivity(intent)
            }
        }



        user_name.text = currentUser?.displayName.toString()
        Glide.with(context).load(currentUser?.photoUrl).apply(RequestOptions()).into(foto_profil)



        logoutTab.setOnClickListener {
            if (currentUser != null) {
                FirebaseAuth.getInstance().signOut()
                val intent = Intent(context, mainActivity::class.java)
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
    }

    private fun alertLaporan(judul: String) {
        val mDialogView = LayoutInflater.from(context).inflate(R.layout.dialog_kebakaran, null)


        val mBuilder = AlertDialog.Builder(this.context)
            .setView(mDialogView)


        val mAlertDialog = mBuilder.show()

        mDialogView.btnSubmitKebakaran.setOnClickListener {
            val username = user_name.text.toString()
            val lokasi = lokasi_user.text.toString()
            mAlertDialog.dismiss()
            db.collection("laporan").add(
                addLaporan(
                    username,
                    lokasi,
                    judul
                )
            )
                .addOnSuccessListener {
                    val dialogBuild =
                        LayoutInflater.from(context).inflate(R.layout.laporan_dialog, null)
                    val build = AlertDialog.Builder(this.context)
                        .setView(dialogBuild)

                    val alertdialogshow = build.show()

                    dialogBuild.sukses_btn.setOnClickListener {
                        alertdialogshow.dismiss()
                    }
                }.addOnFailureListener {
                    Toast.makeText(context, "${it.message}", Toast.LENGTH_SHORT).show()
                }
        }
        mDialogView.btnCancelKebakaran.setOnClickListener {
            mAlertDialog.cancel()
        }
    }
}