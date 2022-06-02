package andlima.hafizhfy.listnewscomposemvvm.api

import andlima.hafizhfy.listnewscomposemvvm.data.GetAllNewsResponseItem
import andlima.hafizhfy.listnewscomposemvvm.data.GetAllStaffResponseItem
import retrofit2.http.GET

interface ApiService {
    @GET("news")
    suspend fun getAllNews() : List<GetAllNewsResponseItem>

    @GET("staf")
    suspend fun getAllStaff() : List<GetAllStaffResponseItem>

}