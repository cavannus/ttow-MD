package id.cavannus.thetaleofwayang.core.utils

import id.cavannus.thetaleofwayang.core.data.source.local.entity.FavoriteEntity
import id.cavannus.thetaleofwayang.core.data.source.local.entity.SearchEntity
import id.cavannus.thetaleofwayang.core.data.source.local.entity.StoriesEntity
import id.cavannus.thetaleofwayang.core.data.source.local.entity.WayangEntity
import id.cavannus.thetaleofwayang.core.data.source.remote.response.StoriesResponse
import id.cavannus.thetaleofwayang.core.data.source.remote.response.WayangResponse
import id.cavannus.thetaleofwayang.core.domain.model.Stories
import id.cavannus.thetaleofwayang.core.domain.model.Wayang

object DataMapper {
    //HISTORY
    fun mapEntitiesToDomain(input: List<WayangEntity>): List<Wayang> =
        input.map {
            Wayang(
                id_wayang = it.id_wayang,
                foto_wayang = it.foto_wayang,
                foto_wayang2 = it.foto_wayang2,
                nm_wayang = it.nm_wayang,
                watak_wayang = it.watak_wayang,
            )
        }

    fun mapResponsesToEntitiesByName(input: List<WayangResponse>): List<WayangEntity> =
            input.map {
                WayangEntity(
                    id_wayang = it.id_wayang,
                    foto_wayang = it.foto,
                    foto_wayang2 = it.foto2,
                    nm_wayang = it.nm_wayang,
                    watak_wayang = it.watak_wayang,
                )
            }

    fun mapEntitiesToDomainByName(input: List<WayangEntity>): List<Wayang> =
            input.map {
                Wayang(
                    id_wayang = it.id_wayang,
                    foto_wayang = it.foto_wayang,
                    foto_wayang2 = it.foto_wayang2,
                    nm_wayang = it.nm_wayang,
                    watak_wayang = it.watak_wayang,
                )
            }


    //STORIES
    fun mapResponsesToEntitiesStory(input: List<StoriesResponse>): List<StoriesEntity> {
        val wayangList = ArrayList<StoriesEntity>()
        input.map {
            val wayang = StoriesEntity(
                id_cerita = it.id_cerita,
                judul = it.judul,
                cerita = it.cerita,
                sumber = it.sumber,
                tokoh = it.tokoh
            )
            wayangList.add(wayang)
        }
        return wayangList
    }

    fun mapEntitiesToDomainStoryList(input: List<StoriesEntity>): List<Stories> =
        input.map {
            Stories(
                id_cerita = it.id_cerita,
                judul = it.judul,
                cerita = it.cerita,
                sumber = it.sumber,
                tokoh = it.tokoh
            )
        }

    //SEARCH
    fun mapResponsesToEntitiesSearch(input: List<WayangResponse>): List<SearchEntity> {
        val wayangList = ArrayList<SearchEntity>()
        input.map {
            val wayang = SearchEntity(
                    id_wayang = it.id_wayang,
                    foto_wayang = it.foto,
                    foto_wayang2 = it.foto2,
                    nm_wayang = it.nm_wayang,
                    watak_wayang = it.watak_wayang,
            )
            wayangList.add(wayang)
        }
        return wayangList
    }

    fun mapEntitiesToDomainSearch(input: List<SearchEntity>): List<Wayang> =
            input.map {
                Wayang(
                        id_wayang = it.id_wayang,
                        foto_wayang = it.foto_wayang,
                        foto_wayang2 = it.foto_wayang2,
                        nm_wayang = it.nm_wayang,
                        watak_wayang = it.watak_wayang,
                )
            }

    //FAVORITE
    fun mapEntitiesToDomainFavoriteList(input: List<FavoriteEntity>): List<Wayang> =
        input.map {
            Wayang(
                id_wayang = it.id_wayang,
                foto_wayang = it.foto_wayang,
                foto_wayang2 = it.foto_wayang2,
                nm_wayang = it.nm_wayang,
                watak_wayang = it.watak_wayang,
            )
        }

    fun mapDomainToEntitiesFavorite(input: Wayang): FavoriteEntity {
        return FavoriteEntity(
                id_wayang = input.id_wayang,
                foto_wayang = input.foto_wayang,
                foto_wayang2 = input.foto_wayang2,
                nm_wayang = input.nm_wayang,
                watak_wayang = input.watak_wayang,
        )
    }
}