package com.panic.panicapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.panic.panicapp.Databases.dataList
import com.panic.panicapp.Function.NumberFormatter
import com.panic.panicapp.R
import kotlinx.android.synthetic.main.layout_recycler.view.*

class MyRecyclerAdapter :
    RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder>() {
    val listUser = ArrayList<dataList>()

    fun addListUser(users: List<dataList>) {
        listUser.clear()
        listUser.addAll(users)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.layout_recycler, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return listUser.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        try {
            val user: dataList = listUser[position]
            val time = NumberFormatter.formatDate(user.waktu!!)

            holder.textViewKejadian.text = user.jenis_laporan
            holder.textViewNama.text = user.nama_pelapor
            holder.textViewAlamat.text = user.lokasi
            holder.textViewTime.text = time


        } catch (e: KotlinNullPointerException) {
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewKejadian: TextView = itemView.txtKejadian
        val textViewNama: TextView = itemView.txtNama
        val textViewAlamat: TextView = itemView.txtAlamat
        val textViewTime: TextView = itemView.txtTime
    }


}