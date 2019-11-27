package com.panic.panicapp.Fragment


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
import com.google.firebase.firestore.Query
import com.panic.panicapp.Databases.dataList
import com.panic.panicapp.Adapter.MyRecyclerAdapter
import com.panic.panicapp.R
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter
import jp.wasabeef.recyclerview.adapters.SlideInBottomAnimationAdapter
import kotlinx.android.synthetic.main.fragment_profil.*

/**
 * A simple [Fragment] subclass.
 */
class newsFragment : Fragment() {

    private val db = FirebaseFirestore.getInstance()
    lateinit var myAdapter: MyRecyclerAdapter

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

        myAdapter = MyRecyclerAdapter(this.context!!)

        var menuView = false



        menu.setOnMenuToggleListener {
            if (menuView == false) {
                menuView = true
                bg_text_1.visibility = View.VISIBLE
                bg_text_2.visibility = View.VISIBLE
                bg_text_3.visibility = View.VISIBLE
            } else {
                menuView = false
                bg_text_1.visibility = View.GONE
                bg_text_2.visibility = View.GONE
                bg_text_3.visibility = View.GONE

            }


        }

        myRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = ScaleInAnimationAdapter(SlideInBottomAnimationAdapter(myAdapter)).apply {
                setFirstOnly(false)
            }
        }



        laporan.addSnapshotListener { _, e ->
            if (e != null) {
                Log.w(TAG, "Listen failed.", e)
                return@addSnapshotListener
            }
            laporan.orderBy("waktu", Query.Direction.DESCENDING).get().addOnSuccessListener {
                val dataList: List<dataList> = it!!.toObjects(dataList::class.java)

                myAdapter.addListUser(dataList)
            }

            menu_sort_waktu.setOnClickListener {
                laporan.orderBy("waktu", Query.Direction.DESCENDING).get().addOnSuccessListener {
                    val dataList: List<dataList> = it!!.toObjects(dataList::class.java)

                    myAdapter.addListUser(dataList)
                }
            }
            menu_sort_nama.setOnClickListener {
                laporan.orderBy("nama_pelapor", Query.Direction.ASCENDING).get()
                    .addOnSuccessListener {
                        val dataList: List<dataList> = it!!.toObjects(dataList::class.java)

                        myAdapter.addListUser(dataList)
                    }
            }

        }


    }


}
