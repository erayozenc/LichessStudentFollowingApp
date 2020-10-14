package com.example.lichessapp2.api

import com.example.lichessapp2.model.Student
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface StudentAPI {

    @GET("/api/user/{username}")
    suspend fun getData(@Path("username") username : String) : Response<Student>
}