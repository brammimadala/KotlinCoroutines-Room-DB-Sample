package com.lasys.rxjavasampledb.db.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(type: T): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert1(type: T): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(type: List<T>?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll1(type: List<T>?)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(obj: T):Int

    @Delete
    fun delete(obj: T):Int

    /*@Delete
    suspend fun delete(obj: T):Int*/

}