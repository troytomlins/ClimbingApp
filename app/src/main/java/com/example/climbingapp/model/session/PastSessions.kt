package com.example.climbingapp.model.session

class PastSessions(
    var sessions: MutableList<Session> = mutableListOf()
) {
    fun getPastSessions(): MutableList<Session> {
        return sessions
    }

    fun addSessionToPastSessions(session: Session) {
        sessions.add(session)
    }

    fun getMostRecentSession(): Session {
        return sessions[-1]
    }
}