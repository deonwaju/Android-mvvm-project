package com.deonolarewaju.hilttest.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BlogDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(blogEntity: BlogCacheEntity): Long

/* might need to come back */
    @Query("SELECT * FROM blogs")
    suspend fun get(): List<BlogCacheEntity>

}