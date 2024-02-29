package com.example.upstoxholding.src.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.upstoxholding.src.model.HoldingsData
import java.text.DecimalFormat

@Composable
fun HoldingsCard(holdingsData: HoldingsData){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 1.dp)
            .background(Color.White)
            .padding(16.dp)
    ) {
        Column (
            horizontalAlignment = AbsoluteAlignment.Left
        ){
            Text(
                text = holdingsData.symbol,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = holdingsData.quantity.toString())
        }
        Column (
            horizontalAlignment = AbsoluteAlignment.Right
        ){
            Text(text = "LTP: ₹ ${holdingsData.ltp}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "P/L: ₹ ${DecimalFormat("0.00").format(holdingsData.pnl)}")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HoldingsCardPreview(){
    HoldingsCard(holdingsData = HoldingsData(
        symbol = "TCS",
        quantity = 10,
        ltp = 3250.5,
        avgPrice = 2480.3,
        close = 3312.0,
        )
    )
}