package com.deonolarewaju.hilttest.remote.retrofit

import com.deonolarewaju.hilttest.model.BlogModel
import com.deonolarewaju.hilttest.util.EntityMapper
import javax.inject.Inject

class NetworkMapper @Inject constructor(): EntityMapper<BlogNetworkEntity, BlogModel> {

    override fun mapFromEntity(entity: BlogNetworkEntity): BlogModel {
        return BlogModel(
            id = entity.id,
            title = entity.title,
            body = entity.body,
            image = entity.image,
            category = entity.category
        )
    }

    override fun mapToEntity(domainModel: BlogModel): BlogNetworkEntity {
        return BlogNetworkEntity(
            id = domainModel.id,
            title = domainModel.title,
            body = domainModel.body,
            image = domainModel.image,
            category = domainModel.category
        )
    }

    fun mapFromEntityList(entities: List<BlogNetworkEntity>): List<BlogModel> {
        return entities.map { mapFromEntity(it) }
    }

}