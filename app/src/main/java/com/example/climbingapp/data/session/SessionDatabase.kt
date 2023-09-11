package com.example.climbingapp.data.session

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.climbingapp.data.BaseConverter

// COMPLETED
@Database(entities = [Session::class], version = 1, exportSchema = false)
@TypeConverters(BaseConverter::class)
abstract class SessionDatabase : RoomDatabase() {

    abstract fun sessionDao(): SessionDao

    companion object{
        @Volatile
        private var Instance: SessionDatabase? = null

        fun getDatabase(context: Context): SessionDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context = context, SessionDatabase::class.java, "session_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}