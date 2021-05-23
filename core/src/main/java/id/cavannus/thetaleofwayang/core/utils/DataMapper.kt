package id.cavannus.thetaleofwayang.core.utils

import id.cavannus.thetaleofwayang.core.data.source.local.entity.WayangEntity
import id.cavannus.thetaleofwayang.core.data.source.remote.response.WayangResponse
import id.cavannus.thetaleofwayang.core.domain.model.Wayang

object DataMapper {
    fun mapResponsesToEntities(input: List<WayangResponse>): List<WayangEntity> {
        val tourismList = ArrayList<WayangEntity>()
        input.map {
            val tourism = WayangEntity(
                id = it.id,
                nama = it.nama,
                golongan = it.golongan,
                ayah = it.ayah,
                ibu = it.ibu,
                anak = it.anak,
                image_url = it.image_url,
                isFavorite = false
            )
            tourismList.add(tourism)
        }
        return tourismList
    }

    fun mapEntitiesToDomain(input: List<WayangEntity>): List<Wayang> =
        input.map {
            Wayang(
                id = it.id,
                nama = it.nama,
                golongan = it.golongan,
                ayah = it.ayah,
                ibu = it.ibu,
                anak = it.anak,
                image_url = it.image_url,
                isFavorite = it.isFavorite
            )
        }
    fun mapDomainToEntity(input: Wayang) = WayangEntity(
        id = input.id,
        nama = input.nama,
        golongan = input.golongan,
        ayah = input.ayah,
        ibu = input.ibu,
        anak = input.anak,
        image_url = input.image_url,
        isFavorite = input.isFavorite
    )
}