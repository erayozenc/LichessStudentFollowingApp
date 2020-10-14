package com.example.lichessapp2.util

import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat

object TimeUtil {

    fun calculateCreatedTime(timeStamp : Long) : String {
        val createdAt = timeStampToString(timeStamp)
        val now = timeStampToString(System.currentTimeMillis())

        val sdf = SimpleDateFormat("dd/MM/yyyy")
        val date1 = sdf.parse(createdAt)
        val date2 = sdf.parse(now)

        val difference = date2.time - date1.time
        return "Created at : " + timeStampToString(difference)
    }

    fun calculateLastSeenTime(timeStamp : Long) : String{
        val lastSeen = timeStampToString(timeStamp)
        val now = timeStampToString(System.currentTimeMillis())

        val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm")
        val date1 = sdf.parse(lastSeen)
        val date2 = sdf.parse(now)

        var difference = date2.time - date1.time

        val minutes = difference / 60 / 1000
        val hours = difference / 60 / 1000 / 60
        val days = difference / 60 / 1000 / 60 / 24
        val months = difference / 60 / 1000 / 60 / 24 / (365/12)
        val years = difference / 60 / 1000 / 60 / 24 / 365

        return when {
            minutes < 240 -> "$minutes minutes ago"
            hours < 48 -> "$hours hours ago"
            days < 61 -> "$days days ago"
            months < 24 -> "$months months ago"
            else -> "$years years ago"
        }
    }

    private fun timeStampToString(timeStamp: Long) : String{
        val stamp = Timestamp(timeStamp)
        val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm")
        val date = sdf.format((Date(stamp.time)))

        return date.toString()
    }
}