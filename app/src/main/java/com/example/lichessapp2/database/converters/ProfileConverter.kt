package com.example.lichessapp2.database.converters

import androidx.room.TypeConverter
import com.example.lichessapp2.model.Profile
import com.google.gson.Gson

class ProfileConverter {

    @TypeConverter
    fun fromGson(gson : String) : Profile {
        return Gson().fromJson(gson,Profile::class.java)
    }

    @TypeConverter
    fun toGson(profile: Profile) : String{
        return Gson().toJson(profile)
    }
}