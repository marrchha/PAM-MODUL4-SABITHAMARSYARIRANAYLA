package com.example.halamantiket

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.halamantiket.screen.TicketBookingScreen
import com.example.halamantiket.ui.theme.HalamanTiketTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HalamanTiketTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    // ---------- State di-hoisting ke parent (MainActivity) ----------
                    var hargaTiket by remember { mutableStateOf(150000) }
                    var jumlahTiket by remember { mutableStateOf(1) }
                    var namaPembeli by rememberSaveable { mutableStateOf("") }

                    var statusMessage by remember { mutableStateOf("") }
                    var isProcessing by remember { mutableStateOf(false) }
                    var orderTrigger by remember { mutableStateOf(0) }

                    // ---------- Side-effect 1: validasi nama kosong ----------
                    LaunchedEffect(namaPembeli) {
                        if (namaPembeli.isBlank()) {
                            statusMessage = "Nama masih kosong"
                        }
                    }

                    // ---------- Side-effect 2: proses pemesanan (delay 5 detik) ----------
                    LaunchedEffect(orderTrigger) {
                        if (orderTrigger > 0) {
                            isProcessing = true
                            statusMessage = "Memproses pesanan........."
                            delay(5000)
                            statusMessage = "Tiket telah dipesan"
                            isProcessing = false
                        }
                    }

                    TicketBookingScreen(
                        modifier = Modifier.padding(innerPadding),
                        hargaTiket = hargaTiket,
                        jumlahTiket = jumlahTiket,
                        namaPembeli = namaPembeli,
                        statusMessage = statusMessage,
                        isProcessing = isProcessing,
                        onNamaChange = { namaPembeli = it },
                        onJumlahTambah = { jumlahTiket++ },
                        onJumlahKurang = { if (jumlahTiket > 1) jumlahTiket-- },
                        onPesanClick = {
                            if (namaPembeli.isBlank()) {
                                statusMessage = "Nama masih kosong"
                            } else {
                                orderTrigger++
                            }
                        }
                    )
                }
            }
        }
    }
}