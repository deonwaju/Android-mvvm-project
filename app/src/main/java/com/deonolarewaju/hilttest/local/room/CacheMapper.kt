package com.deonolarewaju.hilttest.local.room

import com.deonolarewaju.hilttest.model.BlogModel
import com.deonolarewaju.hilttest.util.EntityMapper
import javax.inject.Inject

class CacheMapper @Inject constructor():EntityMapper<BlogCacheEntity, BlogModel> {
    override fun mapFromEntity(entity: BlogCacheEntity): BlogModel {
        return BlogModel(
            id = entity.id,
            title = entity.title,
            body = entity.body,
            image = entity.image,
            category = entity.category
        )
    }

    override fun mapToEntity(domainModel: BlogModel): BlogCacheEntity {
       return BlogCacheEntity(
           id = domainModel.id,
           title = domainModel.title,
           body = domainModel.body,
           image = domainModel.image,
           category = domainModel.category
       )
    }

    fun mapFromEntityList(entities: List<BlogCacheEntity>): List<BlogModel> {
        return entities.map { mapFromEntity(it) }
    }
}