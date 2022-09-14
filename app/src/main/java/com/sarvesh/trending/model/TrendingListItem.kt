package com.sarvesh.trending.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TrendingListItem")
data class TrendingListItem(
    val builtBy: List<BuiltBy>?,
    val description: String?,
    val forks: Int?,
    val language: String?,
    val languageColor: String?,
    val rank: Int?,
    val repositoryName: String?,
    val since: String?,
    val starsSince: Int?,
    val totalStars: Int?,
    val url: String?,
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val username: String?,
    var isSelected:Boolean=false
)