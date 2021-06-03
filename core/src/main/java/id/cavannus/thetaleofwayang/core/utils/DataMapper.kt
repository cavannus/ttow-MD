package id.cavannus.thetaleofwayang.core.utils

import id.cavannus.thetaleofwayang.core.data.source.local.entity.SearchWayangEntity
import id.cavannus.thetaleofwayang.core.data.source.local.entity.StoriesEntity
import id.cavannus.thetaleofwayang.core.data.source.local.entity.WayangEntity
import id.cavannus.thetaleofwayang.core.data.source.remote.response.StoriesResponse
import id.cavannus.thetaleofwayang.core.data.source.remote.response.WayangResponse
import id.cavannus.thetaleofwayang.core.domain.model.Stories
import id.cavannus.thetaleofwayang.core.domain.model.Wayang

object DataMapper {
    fun mapResponsesToEntities(input: List<WayangResponse>): List<WayangEntity> {
        val wayangList = ArrayList<WayangEntity>()
        input.map {
            val wayang = WayangEntity(
                id_wayang = it.id_wayang,
                foto_wayang = it.foto,
                nm_wayang = it.nm_wayang,
                watak_wayang = it.watak_wayang,
                isFavorite = false
            )
            wayangList.add(wayang)
        }
        return wayangList
    }

    fun mapEntitiesToDomain(input: List<WayangEntity>): List<Wayang> =
        input.map {
            Wayang(
                id_wayang = it.id_wayang,
                foto_wayang = it.foto_wayang,
                nm_wayang = it.nm_wayang,
                watak_wayang = it.watak_wayang,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Wayang) = WayangEntity(
        id_wayang = input.id_wayang,
        foto_wayang = input.foto_wayang,
        nm_wayang = input.nm_wayang,
        watak_wayang = input.watak_wayang,
        isFavorite = input.isFavorite
    )

    fun mapResponsesToEntitiesStory(input: List<StoriesResponse>): List<StoriesEntity> {
        val wayangList = ArrayList<StoriesEntity>()
        input.map {
            val wayang = StoriesEntity(
                id_wayang = it.id_wayang,
                nm_wayang = it.nm_wayang,
                watak_wayang = it.watak_wayang,
                isFavorite = false
            )
            wayangList.add(wayang)
        }
        return wayangList
    }

    fun mapEntitiesToDomainStory(input: List<StoriesEntity>): List<Stories> =
        input.map {
            Stories(
                id_wayang = it.id_wayang,
                nm_wayang = it.nm_wayang,
                watak_wayang = it.watak_wayang,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntityStory(input: Stories) = StoriesEntity(
        id_wayang = input.id_wayang,
        nm_wayang = input.nm_wayang,
        watak_wayang = input.watak_wayang,
        isFavorite = input.isFavorite
    )

    fun mapSearchResponsesToEntities(input: List<WayangResponse>): List<SearchWayangEntity> {
        val wayangList = ArrayList<SearchWayangEntity>()
        input.map {
            val wayang = SearchWayangEntity(
                    id_wayang = it.id_wayang,
                    foto_wayang = it.foto,
                    nm_wayang = it.nm_wayang,
                    watak_wayang = it.watak_wayang,
                    isFavorite = false
            )
            wayangList.add(wayang)
        }
        return wayangList
    }

    fun mapSearchEntitiesToDomain(input: List<SearchWayangEntity>): List<Wayang> =
            input.map {
                Wayang(
                        id_wayang = it.id_wayang,
                        foto_wayang = it.foto_wayang,
                        nm_wayang = it.nm_wayang,
                        watak_wayang = it.watak_wayang,
                        isFavorite = it.isFavorite
                )
            }
}