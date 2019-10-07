package com.panic.panicapp


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter
import jp.wasabeef.recyclerview.adapters.SlideInBottomAnimationAdapter
import kotlinx.android.synthetic.main.fragment_profil.*
import kotlinx.android.synthetic.main.setting_layout.*

/**
 * A simple [Fragment] subclass.
 */
class ProfilFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profil, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val user = ArrayList<dataList>()

        user.add(dataList("Kebakaran","Tio Misbaqul","Jl.Dewi Sartika"))
        user.add(dataList("Kecelakaan","Achmad Aries","Jl.Apa Aja Boleh"))
        user.add(dataList("Kriminalistas","Abdillahi Aufal","Jl.Soekarno Hatta"))
        user.add(dataList("Kecelakaan","Zaidan Azmi","Jl.Pasar Lawang"))
        user.add(dataList("Kebakaran","Anjab Abdullah","Jl.Pakisjajar Tengah"))
        user.add(dataList("Kebakaran","Felic Zevana","Jl.Ciliwung"))
        user.add(dataList("Kebakaran","Tio Misbaqul","Jl.Dewi Sartika"))
        user.add(dataList("Kecelakaan","Achmad Aries","Jl.Apa Aja Boleh"))
        user.add(dataList("Kriminalistas","Abdillahi Aufal","Jl.Soekarno Hatta"))
        user.add(dataList("Kecelakaan","Zaidan Azmi","Jl.Pasar Lawang"))
        user.add(dataList("Kebakaran","Anjab Abdullah","Jl.Pakisjajar Tengah"))
        user.add(dataList("Kebakaran","Felic Zevana","Jl.Ciliwung"))

        MyRecyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        val myAdapter = SlideInBottomAnimationAdapter(MyRecyclerAdapter(user))
        MyRecyclerView.adapter = ScaleInAnimationAdapter(myAdapter).apply{
            setFirstOnly(false)
        }

    }


}
