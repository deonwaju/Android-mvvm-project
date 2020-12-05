package com.deonolarewaju.hilttest.repository

import com.deonolarewaju.hilttest.local.room.BlogDao
import com.deonolarewaju.hilttest.local.room.CacheMapper
import com.deonolarewaju.hilttest.model.BlogModel
import com.deonolarewaju.hilttest.remote.retrofit.BlogRetrofit
import com.deonolarewaju.hilttest.remote.retrofit.NetworkMapper
import com.deonolarewaju.hilttest.util.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MainRepository constructor(
    private val blogDao: BlogDao,
    private val blogRetrofit: BlogRetrofit,
    private val cacheMapper: CacheMapper,
    private val networkMapper: NetworkMapper
) {

    suspend fun getBlog(): Flow<DataState<List<BlogModel>>> = flow {
        emit(DataState.Loading)
        delay(1000)
        try {
//            retrieve data from network
            val networkBlogs = blogRetrofit.getBlogs()
            val blogs = networkMapper.mapFromEntityList(networkBlogs)
            for (blog in blogs) {
//                cache retrieved data to database
                blogDao.insert(cacheMapper.mapToEntity(blog))
            }
//            retrieve cached data from database
            val cachedBlogs= blogDao.get()
//            emit or show data to ui after retrieving from cache
            emit(DataState.Success(cacheMapper.mapFromEntityList(cachedBlogs)))
        }catch (e:Exception){
//            error exception
            emit(DataState.Error(e))
        }
    }

}