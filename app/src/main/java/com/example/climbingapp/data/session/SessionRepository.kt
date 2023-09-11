package com.example.climbingapp.data.session

import kotlinx.coroutines.flow.Flow

interface SessionRepository {

    /**
     * Save session
     * @param session Session to save
     */
    suspend fun saveSession(session: Session)

    /**
     * Get 10 sessions
     * @param offset Offset for paging
     * @return List of 10 sessions in flow state
     */
    fun getPageSessions(offset: Int) : Flow<List<Session>>

    /**
     * Gets all Sessions
     * @return List of all sessions in a flow state
     */
    fun getAllSessions() : Flow<List<Session>>

    /**
     * Get session
     * @param id Session ID
     * @return Session in flow state
     */
    fun getSession(id: Int) : Flow<Session>

    /**
     * Delete Session
     * @param session Session to delete
     */
    fun deleteSession(session: Session)
}