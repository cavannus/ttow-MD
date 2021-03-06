package id.cavannus.thetaleofwayang.core.data.source.remote

import android.util.Log
import id.cavannus.thetaleofwayang.core.data.source.remote.network.ApiResponse
import id.cavannus.thetaleofwayang.core.data.source.remote.network.ApiService
import id.cavannus.thetaleofwayang.core.data.source.remote.response.StoriesResponse
import id.cavannus.thetaleofwayang.core.data.source.remote.response.WayangResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {
    //STORIES
    suspend fun getAllStories(query: String): Flow<ApiResponse<List<StoriesResponse>>> {
        return flow {
            try {
                val response = apiService.getListStories(query)
                val dataArray = response.story
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.story))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    //SEARCH
    suspend fun searchWayang(query: String): Flow<ApiResponse<List<WayangResponse>>> {
        return flow {
            try{
                val response = apiService.searchWayang(query)
                val dataArray = response.wayangs
                if(dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.wayangs))
                }else{
                    emit(ApiResponse.Empty)
                }
            }catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}