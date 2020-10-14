package com.example.lichessapp2.database.converters

import androidx.room.TypeConverter
import com.example.lichessapp2.model.Count
import com.example.lichessapp2.model.Profile
import com.google.gson.Gson

class CountConverter {

    @TypeConverter
    fun fromGson(gson : String) : Count {
        return Gson().fromJson(gson, Count::class.java)
    }

    @TypeConverter
    fun toGson(count: Count) : String{
        return Gson().toJson(count)
    }
}