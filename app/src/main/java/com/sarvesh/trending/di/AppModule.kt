package com.sarvesh.trending.di

import android.content.Context
import com.sarvesh.trending.data.db.AppDatabase
import com.sarvesh.trending.data.db.TrendingDao
import com.sarvesh.trending.data.remote.TrendingRemoteDataSource
import com.sarvesh.trending.data.remote.TrendingService
import com.sarvesh.trending.repository.TrendingRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl("https://gh-trending-api.herokuapp.com/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideCharacterService(retrofit: Retrofit): TrendingService = retrofit.create(TrendingService::class.java)

    @Singleton
    @Provides
    fun provideCharacterRemoteDataSource(trendingService: TrendingService) = TrendingRemoteDataSource(trendingService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideCharacterDao(db: AppDatabase) = db.trendingDaoDao()

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: TrendingRemoteDataSource,
                          localDataSource: TrendingDao) =
        TrendingRepository(remoteDataSource, localDataSource)
}