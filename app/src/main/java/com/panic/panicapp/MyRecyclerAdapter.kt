package com.panic.panicapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_recycler.view.*

class MyRecyclerAdapter(val listUser : ArrayList<dataList>): RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.layout_recycler ,parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return listUser.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user: dataList = listUser[position]

        holder.textViewKejadian.text= user.kejadian
        holder.textViewNama.text= user.nama
        holder.textViewAlamat.text= user.alamat
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val textViewKejadian: TextView = itemView.txtKejadian
        val textViewNama : TextView = itemView.txtNama
        val textViewAlamat : TextView = itemView.txtAlamat


    }
}