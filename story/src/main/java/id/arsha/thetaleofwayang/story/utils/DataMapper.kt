package id.arsha.thetaleofwayang.story.utils

//import id.arsha.thetaleofwayang.story.data.source.local.entity.StoriesEntity
//import id.arsha.thetaleofwayang.story.data.source.remote.response.StoriesResponse
//import id.arsha.thetaleofwayang.story.domain.model.Stories
//
//object DataMapper {
//    fun mapResponsesToEntitiesStory(input: List<StoriesResponse>): List<StoriesEntity> {
//        val tourismList = ArrayList<StoriesEntity>()
//        input.map {
//            val tourism = StoriesEntity(
//                    id = it.id,
//                    nm_wayang = it.nm_wayang,
//                    desc_wayang = it.desc_wayang,
//                    stry_wayang = it.stry_wayang,
//                    isFavorite = false
//            )
//            tourismList.add(tourism)
//        }
//        return tourismList
//    }
//
//    fun mapEntitiesToDomainStory(input: List<StoriesEntity>): List<Stories> =
//            input.map {
//                Stories(
//                        id = it.id,
//                        nm_wayang = it.nm_wayang,
//                        desc_wayang = it.desc_wayang,
//                        stry_wayang = it.stry_wayang,
//                        isFavorite = it.isFavorite
//                )
//            }
//    fun mapDomainToEntityStory(input: Stories) = StoriesEntity(
//            id = input.id,
//            nm_wayang = input.nm_wayang,
//            desc_wayang = input.desc_wayang,
//            stry_wayang = input.stry_wayang,
//            isFavorite = input.isFavorite
//    )
//}