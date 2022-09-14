package com.sarvesh.trending.repository

import com.sarvesh.trending.data.db.TrendingDao
import com.sarvesh.trending.data.remote.TrendingRemoteDataSource
import com.sarvesh.trending.utils.performGetOperation
import javax.inject.Inject

class TrendingRepository @Inject constructor(
    private val remoteDataSource: TrendingRemoteDataSource,
    private val localDataSource: TrendingDao
) {

    fun getCharacters() = performGetOperation(
        databaseQuery = {
            localDataSource.getAllCharacters()
                        },
        networkCall = {
            remoteDataSource.getTrending()
                      },
        saveCallResult = {
            localDataSource.delete()
            localDataSource.insertAll(it)
        }
    )
}