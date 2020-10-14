package com.example.lichessapp2.di

import android.content.Context
import androidx.room.Room
import com.example.lichessapp2.database.StudentDatabase
import com.example.lichessapp2.util.Constants.STUDENT_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideStudentDatabase(
        @ApplicationContext app : Context
    ) = Room.databaseBuilder(
        app,
        StudentDatabase::class.java,
        STUDENT_DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideStudentDao(db : StudentDatabase) = db.getDao()
}