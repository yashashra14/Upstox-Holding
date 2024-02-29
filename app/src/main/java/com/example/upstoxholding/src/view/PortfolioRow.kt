package com.example.upstoxholding.src.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import java.text.DecimalFormat

@Composable
fun PortfolioRow(titleId: Int,value: Double) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = stringResource(titleId), style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp))
        Text(text = String.format("₹${DecimalFormat("0.00").format(value)}"),)
    }
}