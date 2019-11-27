package com.panic.panicapp.Databases

import com.google.firebase.firestore.ServerTimestamp
import java.util.*

data class dataList(
    val jenis_laporan: String? = "",
    val nama_pelapor: String? = "",
    val lokasi: String? = "",
    val user_photo: String? = "",
    @ServerTimestamp
    val waktu: Date? = null
)