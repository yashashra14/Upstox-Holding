package com.example.upstoxholding.src.interfaces

import com.example.upstoxholding.constants.holdingsDataPath
import com.example.upstoxholding.src.model.HoldingsDataResponse
import retrofit2.http.GET

interface ApiService {
    @GET(holdingsDataPath)
    suspend fun fetchHoldingsData(): HoldingsDataResponse
}
