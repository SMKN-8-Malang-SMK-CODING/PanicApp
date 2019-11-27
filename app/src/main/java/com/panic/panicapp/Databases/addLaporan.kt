package com.panic.panicapp.Databases

import com.google.firebase.firestore.ServerTimestamp
import java.util.*

data class addLaporan (
    val nama_pelapor: String? = "",
    val lokasi: String? = "",
    val jenis_laporan: String? = "",
    val user_photo: String? = "",
    @ServerTimestamp
    val waktu: Date? = null
)