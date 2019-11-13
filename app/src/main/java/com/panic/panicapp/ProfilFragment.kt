package com.panic.panicapp


import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter
import jp.wasabeef.recyclerview.adapters.SlideInBottomAnimationAdapter
import kotlinx.android.synthetic.main.fragment_profil.*
import kotlinx.android.synthetic.main.setting_layout.*

/**
 * A simple [Fragment] subclass.
 */
class ProfilFragment : Fragment() {

    private val db = FirebaseFirestore.getInstance()

    val myAdapter = MyRecyclerAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profil, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val laporan = db.collection("laporan")

        if(spiner_sortir.selectedItem.toString() == "jenis")laporan.orderBy("jenis_laporan")
        if(spiner_sortir.selectedItem.toString() == "nama_pelapor")laporan.orderBy("nama_pelapor")
        if(spiner_sortir.selectedItem.toString() == "lokasi")laporan.orderBy("lokasi")

        myRecyclerView.apply {
            layoutManager  = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = ScaleInAnimationAdapter(SlideInBottomAnimationAdapter(myAdapter)).apply {
                setFirstOnly(false)
            }
        }

        laporan.addSnapshotListener { _, e ->
            if (e != null) {
                Log.w(TAG, "Listen failed.", e)
                return@addSnapshotListener
            }

            laporan.get().addOnSuccessListener {
                val dataList: List<dataList> = it!!.toObjects(dataList::class.java)

                myAdapter.addListUser(dataList)
            }
        }


    }


}
