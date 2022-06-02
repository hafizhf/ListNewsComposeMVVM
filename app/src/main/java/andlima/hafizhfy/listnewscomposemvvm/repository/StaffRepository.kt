package andlima.hafizhfy.listnewscomposemvvm.repository

import andlima.hafizhfy.listnewscomposemvvm.api.ApiService
import andlima.hafizhfy.listnewscomposemvvm.data.GetAllStaffResponseItem
import javax.inject.Inject

class StaffRepository @Inject constructor(private val apiHelper: ApiService) {
    suspend fun getAllStaff() : List<GetAllStaffResponseItem> {
        return apiHelper.getAllStaff()
    }
}