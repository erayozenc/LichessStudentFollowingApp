package com.example.lichessapp2.repository

import com.example.lichessapp2.api.StudentAPI
import com.example.lichessapp2.database.StudentDao
import com.example.lichessapp2.model.Student
import javax.inject.Inject

class StudentRepository @Inject constructor(
    private val dao : StudentDao,
    private val retrofit : StudentAPI
) {

    suspend fun getStudentFromAPI(username : String) = retrofit.getData(username)

    suspend fun insertStudentToRoom(student : Student) = dao.insertStudent(student)

    suspend fun updateStudentInRoom(student: Student) = dao.updateStudent(student)

    fun getAllStudentsFromRoom() = dao.getAllStudents()

    suspend fun deleteStudentFromRoom(student: Student) = dao.deleteStudent(student)

    suspend fun deleteAllStudentsFromRoom() = dao.deleteAllStudents()
}