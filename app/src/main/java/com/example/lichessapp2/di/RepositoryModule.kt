package com.example.lichessapp2.di

import com.example.lichessapp2.api.StudentAPI
import com.example.lichessapp2.database.StudentDao
import com.example.lichessapp2.model.Student
import com.example.lichessapp2.repository.StudentRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideStudentRepository(
        dao: StudentDao,
        retrofit: StudentAPI
    ) : StudentRepository{
        return StudentRepository(dao,retrofit)
    }
}