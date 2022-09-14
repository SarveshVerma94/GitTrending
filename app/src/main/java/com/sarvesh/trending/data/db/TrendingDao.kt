package com.sarvesh.trending.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sarvesh.trending.model.TrendingListItem

@Dao
interface TrendingDao {

    @Query("SELECT * FROM TrendingListItem")
    fun getAllCharacters() : LiveData<List<TrendingListItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<TrendingListItem>)

    @Query("DELETE FROM TrendingListItem")
    fun delete()


}