package com.example.halamantiket.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun TicketBookingScreen(
    modifier: Modifier = Modifier,
    hargaTiket: Int,
    jumlahTiket: Int,
    namaPembeli: String,
    statusMessage: String,
    isProcessing: Boolean,
    onNamaChange: (String) -> Unit,
    onJumlahTambah: () -> Unit,
    onJumlahKurang: () -> Unit,
    onPesanClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = "Pemesanan Tiket", fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Harga Tiket: Rp $hargaTiket")
        Spacer(modifier = Modifier.height(12.dp))

        Text(text = "Nama")
        OutlinedTextField(
            value = namaPembeli,
            onValueChange = onNamaChange,
            placeholder = { Text("Masukkan nama Anda") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Jumlah Tiket")
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(onClick = onJumlahKurang, enabled = jumlahTiket > 1) { Text("-") }
            Text(text = "$jumlahTiket")
            Button(onClick = onJumlahTambah) { Text("+") }
        }
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Total: Rp ${hargaTiket * jumlahTiket}")
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onPesanClick,
            enabled = !isProcessing,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (isProcessing) "Memproses..." else "Pesan Tiket")
        }
        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            if (isProcessing) {
                CircularProgressIndicator(modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(8.dp))
            }
            Text(text = "Status: $statusMessage")
        }
    }
}