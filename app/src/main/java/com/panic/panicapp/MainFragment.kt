package com.panic.panicapp

import android.app.AlertDialog
import android.content.Intent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.dialog_kebakaran.view.*
import kotlinx.android.synthetic.main.fragment_main.*


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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val currentUser = FirebaseAuth.getInstance().currentUser

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
            val mDialogView = LayoutInflater.from(context).inflate(R.layout.dialog_kebakaran, null)

            val mBuilder = AlertDialog.Builder(this.context)
                .setView(mDialogView)
                .setTitle("     Laporkan Kebakaran   ")


            val mAlertDialog = mBuilder.show()

            mDialogView.btnSubmitKebakaran.setOnClickListener{
                mAlertDialog.dismiss()
                Toast.makeText(context, "Berhasil Melaporkan", Toast.LENGTH_SHORT).show()
            }
            mDialogView.btnCancelKebakaran.setOnClickListener{
                mAlertDialog.dismiss()
            }


        }

        kecelakaanTab.setOnClickListener {
            val mDialogView = LayoutInflater.from(context).inflate(R.layout.dialog_kebakaran, null)

            val mBuilder = AlertDialog.Builder(this.context)
                .setView(mDialogView)
                .setTitle("Laporkan Kejadian Kecelakaan")

            val mAlertDialog = mBuilder.show()

            mDialogView.btnSubmitKebakaran.setOnClickListener{
                mAlertDialog.dismiss()
                Toast.makeText(context, "Berhasil Melaporkan", Toast.LENGTH_SHORT).show()
            }
            mDialogView.btnCancelKebakaran.setOnClickListener{
                mAlertDialog.dismiss()
            }
        }
        kriminalTab.setOnClickListener {
            val mDialogView = LayoutInflater.from(context).inflate(R.layout.dialog_kebakaran, null)

            val mBuilder = AlertDialog.Builder(this.context)
                .setView(mDialogView)
                .setTitle("Laporkan Kejadian Kriminalitas")


            val mAlertDialog = mBuilder.show()

            mDialogView.btnSubmitKebakaran.setOnClickListener{
                mAlertDialog.dismiss()
                Toast.makeText(context, "Berhasil Melaporkan", Toast.LENGTH_SHORT).show()
            }
            mDialogView.btnCancelKebakaran.setOnClickListener{

                mAlertDialog.cancel()
            }
        }

        foto_profil.setOnClickListener{
            val intent = Intent(context, settingActivity::class.java)
            startActivity(intent)
        }


    }

}
