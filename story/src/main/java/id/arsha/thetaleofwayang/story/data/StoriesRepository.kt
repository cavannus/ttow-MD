package id.arsha.thetaleofwayang.story.data

//import id.arsha.thetaleofwayang.story.data.source.local.LocalDataSource
//import id.arsha.thetaleofwayang.story.data.source.remote.RemoteDataSource
//import id.arsha.thetaleofwayang.story.data.source.remote.network.ApiResponse
//import id.arsha.thetaleofwayang.story.data.source.remote.response.StoriesResponse
//import id.arsha.thetaleofwayang.story.domain.model.Stories
//import id.arsha.thetaleofwayang.story.domain.repository.IStoriesRepository
//import id.arsha.thetaleofwayang.story.utils.AppExecutors
//import id.arsha.thetaleofwayang.story.utils.DataMapper
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.map
//
//class StoriesRepository(
//    private val remoteDataSource: RemoteDataSource,
//    private val localDataSource: LocalDataSource,
//    private val appExecutors: AppExecutors
//) : IStoriesRepository {
//
//    override fun getAllStories(): Flow<Resource<List<Stories>>> =
//        object : NetworkBoundResource<List<Stories>, List<StoriesResponse>>() {
//            override fun loadFromDB(): Flow<List<Stories>> {
//                return localDataSource.getAllStories().map { DataMapper.mapEntitiesToDomainStory(it) }
//            }
//
//            override fun shouldFetch(data: List<Stories>?): Boolean =
//                data == null || data.isEmpty()
//
//            override suspend fun createCall(): Flow<ApiResponse<List<StoriesResponse>>> =
//                remoteDataSource.getAllStories()
//
//            override suspend fun saveCallResult(data: List<StoriesResponse>) {
//                val storiesList = DataMapper.mapResponsesToEntitiesStory(data)
//                localDataSource.insertStories(storiesList)
//            }
//        }.asFlow()
//
//    override fun getFavoriteStories(): Flow<List<Stories>> {
//        return localDataSource.getFavoriteStories().map { DataMapper.mapEntitiesToDomainStory(it) }
//
//    }
//
//    override fun setFavoriteStories(stories: Stories, state: Boolean) {
//        val storiesEntity = DataMapper.mapDomainToEntityStory(stories)
//        appExecutors.diskIO().execute { localDataSource.setFavoriteStories(storiesEntity, state) }
//    }
//}

