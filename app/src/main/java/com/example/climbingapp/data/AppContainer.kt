package com.example.climbingapp.data

import android.content.Context
import com.example.climbingapp.data.session.OfflineSessionRepository
import com.example.climbingapp.data.session.SessionDatabase
import com.example.climbingapp.data.session.SessionRepository

/**
 * App container for Dependency injection.
 */
interface AppContainer {
    val sessionRepository: SessionRepository
}

/**
 * [AppContainer] implementation that provides instance of [OfflineSessionRepository]
 */
class AppDataContainer(private val context: Context) : AppContainer {
    /**
     * Implementation for [SessionRepository]
     */
    override val sessionRepository: SessionRepository by lazy {
        OfflineSessionRepository(SessionDatabase.getDatabase(context).sessionDao())
    }
}
