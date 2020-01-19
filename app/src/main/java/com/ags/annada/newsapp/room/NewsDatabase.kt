package com.ags.annada.newsapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ags.annada.newsapp.room.daos.ContentDao
import com.ags.annada.newsapp.room.entities.Content

@Database(entities = [Content::class], version = 1)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun contentDao(): ContentDao

    companion object {
        private var INSTANCE: NewsDatabase? = null

        fun getInstance(context: Context): NewsDatabase? {
            if (INSTANCE == null) {
                synchronized(NewsDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        NewsDatabase::class.java, "news.db"
                    ).build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}