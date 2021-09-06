package com.lasys.coroutinesdbsample.di.hilt

import android.content.Context
import com.lasys.coroutinesdbsample.db.AppDatabase
import com.lasys.coroutinesdbsample.repository.SubscriberRepository
import com.lasys.rxjavasampledb.db.dao.SubscriberDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {


    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext appContext: Context): AppDatabase {
        return AppDatabase.getDatabase(appContext)
    }

    @Provides
    fun providesSubscriberDao(appDatabase: AppDatabase): SubscriberDAO =
        appDatabase.getSubscriberDAO()

    @Provides
    fun subscriberRepository(
        subscriberDAO: SubscriberDAO
    ): SubscriberRepository {
        return SubscriberRepository(subscriberDAO)
    }

}