package com.example.upstoxholding
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.upstoxholding.src.screens.HoldingsActivity
import com.example.upstoxholding.src.viewModel.HoldingAppViewModel

class MainActivity : ComponentActivity() {
    private val holdingAppViewModel: HoldingAppViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HoldingsActivity(holdingAppViewModel)
        }
    }
}
