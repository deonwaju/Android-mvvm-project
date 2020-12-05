package com.deonolarewaju.hilttest.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.deonolarewaju.hilttest.local.room.BlogDao
import com.deonolarewaju.hilttest.local.room.MyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun providesMyDataBase(@ApplicationContext context: Context): MyDatabase{
        return Room.databaseBuilder(
            context,
            MyDatabase::class.java,
            MyDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providesBlogDAO(myDatabase: MyDatabase): BlogDao{
        return myDatabase.blogDao()
    }

}