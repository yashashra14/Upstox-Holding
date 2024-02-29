package com.example.upstoxholding.src.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.upstoxholding.R
import com.example.upstoxholding.src.model.HoldingDataStatus
import com.example.upstoxholding.src.view.HoldingsCard
import com.example.upstoxholding.src.view.PortfolioRow
import com.example.upstoxholding.src.viewModel.HoldingAppViewModel
import com.example.upstoxholding.ui.theme.BackgroundColor
import com.example.upstoxholding.ui.theme.SetStatusBarColor
import com.example.upstoxholding.ui.theme.UpstoxHoldingTheme

@Composable
fun HoldingsActivity(holdingAppViewModel: HoldingAppViewModel) {
    DisposableEffect(Unit) {
        holdingAppViewModel.fetchHoldingsData()
        onDispose {}
    }

    UpstoxHoldingTheme (false){
        SetStatusBarColor(color = Color.White)
        Surface(
            color = BackgroundColor
        ) {
            Column{
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .background(colorResource(id = R.color.primary_color))
                        .padding(16.dp),
                ) {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        fontSize = 18.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                    )
                }
                when(holdingAppViewModel.holdingDataStatus.value){
                    HoldingDataStatus.INITIAL -> Box(modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterHorizontally), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                    HoldingDataStatus.SUCCESS -> LazyColumn(
                        Modifier
                        .weight(1f)) {
                        items(holdingAppViewModel.holdings.value) { holding ->
                            HoldingsCard(holdingsData = holding)
                        }
                    }
                    HoldingDataStatus.FAILURE -> Box(modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterHorizontally), contentAlignment = Alignment.Center) {
                        Column {
                            Text(text = stringResource(id = R.string.error_fetching_data))
                            Spacer(modifier = Modifier.height(16.dp))
                            Image(
                                painter = painterResource(id = R.drawable.noun_retry),
                                contentDescription = "arrow",
                                modifier = Modifier
                                    .clickable {
                                        holdingAppViewModel.fetchHoldingsData()
                                    }
                                    .align(Alignment.CenterHorizontally)
                            )
                        }
                    }
                }
                Surface(shadowElevation = 16.dp) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                            .padding(horizontal = 16.dp)
                            .padding(bottom = 24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.baseline_arrow_drop_down_24),
                            contentDescription = "arrow",
                            modifier = Modifier
                                .size(30.dp)
                                .rotate(if (holdingAppViewModel.isPortfolioVisible.value) 0f else 180f)
                                .pointerInput(Unit) {
                                    detectTapGestures(onTap = { holdingAppViewModel.togglePortFolioState() })
                                }
                        )
                        AnimatedVisibility(visible = holdingAppViewModel.isPortfolioVisible.value) {
                            Column {
                                PortfolioRow(
                                    titleId = R.string.current_value,
                                    value = holdingAppViewModel.currentValueTotal.doubleValue
                                )
                                PortfolioRow(
                                    titleId = R.string.total_investment,
                                    value = holdingAppViewModel.totalInvestment.doubleValue
                                )
                                PortfolioRow(
                                    titleId = R.string.todays_profit,
                                    value = holdingAppViewModel.todayPnl.doubleValue
                                )
                                Spacer(modifier = Modifier.height(24.dp))
                            }
                        }
                        PortfolioRow(
                            titleId = R.string.profit_n_loss,
                            value = holdingAppViewModel.totalPnl.doubleValue
                        )
                    }
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HoldingsActivity(viewModel())
}