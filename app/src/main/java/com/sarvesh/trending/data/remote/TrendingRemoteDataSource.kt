package com.sarvesh.trending.data.remote

import javax.inject.Inject

class TrendingRemoteDataSource @Inject constructor(
    private val trendingService: TrendingService
): BaseDataSource() {

    suspend fun getTrending() = getResult { trendingService.getAllTrending() }

}