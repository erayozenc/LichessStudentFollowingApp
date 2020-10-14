package com.example.lichessapp2.di

import com.example.lichessapp2.api.StudentAPI
import com.example.lichessapp2.util.Constants.BASE_URL
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule  {

    @Provides
    @Singleton
    fun provideGsonBuilder() : Gson{
        return GsonBuilder().create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson : Gson) : Retrofit.Builder{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Singleton
    @Provides
    fun provideStudentAPI(retrofit : Retrofit.Builder) : StudentAPI{
        return retrofit.build().create(StudentAPI::class.java)
    }
}