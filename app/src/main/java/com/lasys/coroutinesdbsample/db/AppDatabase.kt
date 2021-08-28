package com.lasys.coroutinesdbsample.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lasys.coroutinesdbsample.db.entity.Subscriber
import com.lasys.rxjavasampledb.db.dao.SubscriberDAO


@Database(
    entities = [Subscriber::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase(){

    companion object{

        const val DATABASE_NAME = "contacts_app_data_database"

        @Volatile
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext.applicationContext, AppDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()


    }

    abstract fun getSubscriberDAO(): SubscriberDAO

}