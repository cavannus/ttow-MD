package id.cavannus.thetaleofwayang.core.data.source.remote.network

import id.cavannus.thetaleofwayang.core.data.source.remote.response.ListStoriesResponse
import id.cavannus.thetaleofwayang.core.data.source.remote.response.ListWayangResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("index")
    suspend fun getList(): ListWayangResponse

    @GET("getcerita.php")
    suspend fun getListStories(
            @Query("tk") tk: String
    ): ListStoriesResponse

    @GET("getwayang.php")
    suspend fun searchWayang(
            @Query("nm") nm: String
    ): ListWayangResponse
}