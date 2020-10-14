package com.example.lichessapp2.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.lichessapp2.model.Student

@Dao
interface StudentDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertStudent(student : Student) : Long

    @Update
    suspend fun updateStudent(student : Student)

    @Query("SELECT * FROM students")
    fun getAllStudents() : LiveData<List<Student>>

    @Delete
    suspend fun deleteStudent(student : Student)

    @Query("DELETE FROM students")
    suspend fun deleteAllStudents()
}