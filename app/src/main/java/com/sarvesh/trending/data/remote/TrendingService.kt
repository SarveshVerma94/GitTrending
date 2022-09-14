package com.sarvesh.trending.data.remote


import com.sarvesh.trending.model.TrendingListItem
import retrofit2.Response
import retrofit2.http.GET


interface TrendingService {
    @GET("repositories/")
    suspend fun getAllTrending() : Response<List<TrendingListItem>>
}
