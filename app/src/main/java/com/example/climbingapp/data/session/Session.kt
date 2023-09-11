package com.example.climbingapp.data.session

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.climbingapp.data.BaseConverter
import java.util.Calendar
import java.util.Date

/**
 * Entity class for Session
 * Tracks climbs in a Session
 */
@Entity(tableName = "session")
@TypeConverters(BaseConverter::class)
data class Session(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val gymId: Int = 0,
    val climbIds: List<Int> = listOf(),
    val highestGrade: Int = 0,
    val date: Date = Date()
)