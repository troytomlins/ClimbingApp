package com.example.climbingapp.data.session

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SessionDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(session: Session)

    @Delete
    fun deleteSession(session: Session)

    @Query("SELECT * FROM session WHERE id = :id")
    fun getSession(id: Int): Flow<Session>

    @Query("SELECT * FROM session")
    fun getAllSessions(): Flow<List<Session>>
}