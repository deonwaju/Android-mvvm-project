package com.deonolarewaju.hilttest.local.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [BlogCacheEntity::class], version = 1)
abstract class MyDatabase: RoomDatabase() {

    abstract fun blogDao(): BlogDao

    companion object{
        var DATABASE_NAME: String = "blog_db"
    }
}