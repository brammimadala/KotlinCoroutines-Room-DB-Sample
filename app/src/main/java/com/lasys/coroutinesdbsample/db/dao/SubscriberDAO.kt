package com.lasys.rxjavasampledb.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.lasys.coroutinesdbsample.db.entity.Subscriber

import java.util.concurrent.Flow


@Dao
interface SubscriberDAO:BaseDao<Subscriber>{

    /*@Query("select * from subscriber")
    fun getContacts(): Flow<List<Subscriber>>?*/

    @Query("select * from subscriber where subscriber_id ==:contactId")
    suspend fun getContact(contactId: Long): Subscriber?

    @Query("select * from subscriber")
    fun getAllSubscribers():LiveData<List<Subscriber>>

    @Query("delete from subscriber")
    fun deleteAll():Int

}


