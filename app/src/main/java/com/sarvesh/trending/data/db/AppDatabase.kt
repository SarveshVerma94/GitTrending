package com.sarvesh.trending.data.db

import android.content.Context
import androidx.room.*
import com.sarvesh.trending.model.Converter
import com.sarvesh.trending.model.TrendingListItem

@TypeConverters (Converter::class)
@Database(entities = [TrendingListItem::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun trendingDaoDao(): TrendingDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, "TrendingListItem")
                .fallbackToDestructiveMigration()
                .build()
    }

}