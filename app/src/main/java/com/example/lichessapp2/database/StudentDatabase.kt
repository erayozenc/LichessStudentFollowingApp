package com.example.lichessapp2.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.lichessapp2.database.converters.CountConverter
import com.example.lichessapp2.database.converters.ProfileConverter
import com.example.lichessapp2.model.Student

@Database(
    entities = [Student::class],
    version = 1
)
@TypeConverters(ProfileConverter::class,CountConverter::class)
abstract class StudentDatabase : RoomDatabase() {
    abstract fun getDao() : StudentDao
}