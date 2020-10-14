package com.example.lichessapp2.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "students")
data class Student(
    val username : String,
    val online : Boolean,
    val profile : Profile?,
    val seenAt : Long,
    val url : String,
    val createdAt : Long,
    val title : String?,
    val count : Count
) : Serializable {
    @PrimaryKey
    var uuid : Int = 0
}