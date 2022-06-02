package andlima.hafizhfy.listnewscomposemvvm.repository

import andlima.hafizhfy.listnewscomposemvvm.api.ApiService
import andlima.hafizhfy.listnewscomposemvvm.data.GetAllNewsResponseItem
import javax.inject.Inject

class NewsRepository @Inject constructor(private val apiHelper: ApiService) {
    suspend fun getAllNews() : List<GetAllNewsResponseItem> {
        return apiHelper.getAllNews()
    }
}