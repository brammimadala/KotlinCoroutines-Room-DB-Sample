package com.lasys.coroutinesdbsample.db.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.lasys.coroutinesdbsample.db.AppDatabase
import com.lasys.coroutinesdbsample.db.entity.Subscriber
import com.lasys.rxjavasampledb.db.dao.SubscriberDAO
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class SubscriberDAOTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: AppDatabase
    private lateinit var dao: SubscriberDAO

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.getSubscriberDAO()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertSubscriberItem() = runBlocking {
        val subscriberItem = Subscriber(1, "Brahmam", "brammimadala@gmail.com")
        dao.insert(subscriberItem)

        val allSubscribers = dao.getAllSubscribers().getOrAwaitValue()

        assertThat(allSubscribers).contains(subscriberItem)
    }

    @Test
    fun deleteSubscriberItem() = runBlocking {
        val subscriberItem = Subscriber(1, "Madala", "madala@gmail.com")
        dao.insert(subscriberItem)
        dao.delete(subscriberItem)

        val allSubscribers = dao.getAllSubscribers().getOrAwaitValue()
        assertThat(allSubscribers).doesNotContain(subscriberItem)

    }
}