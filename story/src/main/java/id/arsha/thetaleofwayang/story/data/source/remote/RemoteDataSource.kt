package id.arsha.thetaleofwayang.story.data.source.remote

//import android.util.Log
//import id.arsha.thetaleofwayang.story.data.source.remote.network.ApiResponse
//import id.arsha.thetaleofwayang.story.data.source.remote.network.ApiService
//import id.arsha.thetaleofwayang.story.data.source.remote.response.StoriesResponse
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.flow
//import kotlinx.coroutines.flow.flowOn

//class RemoteDataSource(private val apiService: ApiService) {
//
//    suspend fun getAllStories(): Flow<ApiResponse<List<StoriesResponse>>> {
//        return flow {
//            try {
//                val response = apiService.getListStories()
//                val dataArray = response.story
//                if (dataArray.isNotEmpty()){
//                    emit(ApiResponse.Success(response.story))
//                } else {
//                    emit(ApiResponse.Empty)
//                }
//            } catch (e : Exception){
//                emit(ApiResponse.Error(e.toString()))
//                Log.e("RemoteDataSource", e.toString())
//            }
//        }.flowOn(Dispatchers.IO)
//    }
//}