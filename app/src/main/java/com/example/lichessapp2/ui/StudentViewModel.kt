package com.example.lichessapp2.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lichessapp2.model.Student
import com.example.lichessapp2.repository.StudentRepository
import com.example.lichessapp2.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class StudentViewModel @ViewModelInject constructor(
    private val repository: StudentRepository
):ViewModel() {

    val student : MutableLiveData<Resource<Student>> = MutableLiveData()

    fun getStudentData(username : String) = viewModelScope.launch {

        student.postValue(Resource.Loading())
        val response = repository.getStudentFromAPI(username)
        println(username)
        println(response.body())
        student.postValue(handleStudentResponse(response))
    }

    private fun handleStudentResponse(response : Response<Student>) : Resource<Student>{
        if (response.isSuccessful){
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }

    fun saveStudent(student : Student) = viewModelScope.launch {
        repository.insertStudentToRoom(student)
        println("Saved Succesfully to room.")
    }

    fun updateStudent(student: Student) = viewModelScope.launch {
        repository.updateStudentInRoom(student)
        println("Updated succesfully.")
    }

    fun showAllStudents() = repository.getAllStudentsFromRoom()

    fun deleteStudent(student : Student) = viewModelScope.launch {
        repository.deleteStudentFromRoom(student)
    }

    fun deleteAllStudents() = viewModelScope.launch {
        repository.deleteAllStudentsFromRoom()
    }
}