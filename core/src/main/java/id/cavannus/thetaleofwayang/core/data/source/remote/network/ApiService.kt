package id.cavannus.thetaleofwayang.core.data.source.remote.network

import id.cavannus.thetaleofwayang.core.data.source.remote.response.ListStoriesResponse
import id.cavannus.thetaleofwayang.core.data.source.remote.response.ListWayangResponse
import retrofit2.http.GET

interface ApiService {
    @GET("index")
    suspend fun getList(): ListWayangResponse

    @GET("index")
    suspend fun getListStories(): ListStoriesResponse
}