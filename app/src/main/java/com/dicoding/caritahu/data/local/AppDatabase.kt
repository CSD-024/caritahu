package com.dicoding.caritahu.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dicoding.caritahu.data.network.model.NewsArticle

@Database(entities = [NewsArticle::class], version = 1, exportSchema = false)
@TypeConverters(SourceTypeConverter::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun dao(): BookmarkDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            synchronized(this) {
                INSTANCE = Room
                    .databaseBuilder(context.applicationContext, AppDatabase::class.java, "cari.db")
                    .fallbackToDestructiveMigration()
                    .build()

                return INSTANCE as AppDatabase
            }
        }
    }
}