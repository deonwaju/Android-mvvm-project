package com.deonolarewaju.hilttest.di

import com.deonolarewaju.hilttest.local.room.BlogDao
import com.deonolarewaju.hilttest.local.room.CacheMapper
import com.deonolarewaju.hilttest.remote.retrofit.BlogRetrofit
import com.deonolarewaju.hilttest.remote.retrofit.NetworkMapper
import com.deonolarewaju.hilttest.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun providesMainRepository(
        blogDao: BlogDao,
        blogRetrofit: BlogRetrofit,
        cacheMapper: CacheMapper,
        networkMapper: NetworkMapper
    ): MainRepository {
        return MainRepository(blogDao, blogRetrofit, cacheMapper, networkMapper)
    }

}
