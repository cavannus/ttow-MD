package id.cavannus.thetaleofwayang.core.data.source.remote.network

import id.cavannus.thetaleofwayang.core.data.source.remote.response.ListWayangResponse
import retrofit2.http.GET

interface ApiService {
    @GET("wayang")
    suspend fun getList(): ListWayangResponse
}