package id.cavannus.thetaleofwayang.core.data

import id.cavannus.thetaleofwayang.core.data.source.local.LocalDataSource
import id.cavannus.thetaleofwayang.core.data.source.local.entity.FavoriteEntity
import id.cavannus.thetaleofwayang.core.data.source.remote.RemoteDataSource
import id.cavannus.thetaleofwayang.core.data.source.remote.network.ApiResponse
import id.cavannus.thetaleofwayang.core.data.source.remote.response.StoriesResponse
import id.cavannus.thetaleofwayang.core.data.source.remote.response.WayangResponse
import id.cavannus.thetaleofwayang.core.domain.model.Stories
import id.cavannus.thetaleofwayang.core.domain.model.Wayang
import id.cavannus.thetaleofwayang.core.domain.repository.IWayangRepository
import id.cavannus.thetaleofwayang.core.utils.AppExecutors
import id.cavannus.thetaleofwayang.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class WayangRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IWayangRepository {
    //HISTORY
    override fun getAllWayang(): Flow<Resource<List<Wayang>>> =
        object : NetworkBoundResource<List<Wayang>, List<WayangResponse>>() {
            override fun loadFromDB(): Flow<List<Wayang>> {
                return localDataSource.getAllWayang().map { DataMapper.mapEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<Wayang>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<WayangResponse>>> =
                remoteDataSource.getAllWayang()

            override suspend fun saveCallResult(data: List<WayangResponse>) {
                val wayangList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertWayang(wayangList)
            }
        }.asFlow()

    //STORIES
    override fun getAllStories(query: String): Flow<Resource<List<Stories>>> =
        object : NetworkBoundResource<List<Stories>, List<StoriesResponse>>() {
            override fun loadFromDB(): Flow<List<Stories>> {
                return localDataSource.getAllStories(query).map { DataMapper.mapEntitiesToDomainStoryList(it) }
            }

            override fun shouldFetch(data: List<Stories>?): Boolean =
                    data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<StoriesResponse>>> =
                remoteDataSource.getAllStories(query)

            override suspend fun saveCallResult(data: List<StoriesResponse>) {
                val storiesList = DataMapper.mapResponsesToEntitiesStory(data)
                localDataSource.insertStories(storiesList)
            }
        }.asFlow()


    //SEARCH
    override fun searchWayang(query: String): Flow<Resource<List<Wayang>>> =
            object : NetworkBoundResource<List<Wayang>, List<WayangResponse>>(){
                override fun loadFromDB(): Flow<List<Wayang>> {
                    return localDataSource.searchWayang(query).map {
                        DataMapper.mapEntitiesToDomainSearch(it)
                    }
                }

                override fun shouldFetch(data: List<Wayang>?): Boolean =
                    data == null || data.isEmpty()

                override suspend fun createCall(): Flow<ApiResponse<List<WayangResponse>>> =
                        remoteDataSource.searchWayang(query)

                override suspend fun saveCallResult(data: List<WayangResponse>) {
                    val wayangList = DataMapper.mapResponsesToEntitiesSearch(data)
                    localDataSource.insertSearchWayang(wayangList)
                }
            }.asFlow()

    //FAVORITE
    override fun getFavoriteWayang(): Flow<List<Wayang>> {
        return localDataSource.getFavoriteWayang().map {
            DataMapper.mapEntitiesToDomainFavorite(it)
        }
    }

    override fun getFavoriteWayangByName(name: String): Flow<Wayang?> {
        return localDataSource.getFavoriteWayangByName(name).map {
            DataMapper.mapEntitiesToDomainFavorite(it)
        }
    }

    override fun addFavoriteWayang(wayang: Wayang) {
        val wayangEntity = DataMapper.mapDomainToEntityFavorite(wayang)
        appExecutors.diskIO().execute { localDataSource.addFavoriteWayang(wayangEntity) }
    }

    override fun delFavoriteWayang(wayang: Wayang) {
        val wayangEntity = DataMapper.mapDomainToEntityFavorite(wayang)
        appExecutors.diskIO().execute { localDataSource.delFavoriteWayang(wayangEntity) }
    }
}

