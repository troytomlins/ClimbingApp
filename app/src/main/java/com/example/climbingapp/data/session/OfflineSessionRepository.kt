package com.example.climbingapp.data.session

import kotlinx.coroutines.flow.Flow


class OfflineSessionRepository(private val sessionDao: SessionDao): SessionRepository {
    override suspend fun saveSession(session: Session) = sessionDao.insert(session)

    override fun getPageSessions(offset: Int): Flow<List<Session>> {
        TODO("Not yet implemented")
    }

    override fun getAllSessions(): Flow<List<Session>> = sessionDao.getAllSessions()

    override fun getSession(id: Int): Flow<Session> = sessionDao.getSession(id)

    override fun deleteSession(session: Session) = sessionDao.deleteSession(session)


}