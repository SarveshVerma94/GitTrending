package com.sarvesh.trending.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converter {
    @TypeConverter
    fun fromString(value: String): List<BuiltBy> {
        val listType = object : TypeToken<List<BuiltBy>>() {}.type
        return Gson().fromJson(value, listType)

    }

    @TypeConverter
    fun fromList(list: List<BuiltBy>): String {
        val gson = Gson()
        return gson.toJson(list)
    }

}