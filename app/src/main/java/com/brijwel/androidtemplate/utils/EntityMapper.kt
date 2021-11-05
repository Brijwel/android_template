package com.brijwel.androidtemplate.utils

/***
 * maps the entity class to and from domain model class
 */
interface EntityMapper<Entity, DomainModel> {

    fun mapFromEntity(entity: Entity): DomainModel

    fun mapToEntity(domainModel: DomainModel): Entity
}
