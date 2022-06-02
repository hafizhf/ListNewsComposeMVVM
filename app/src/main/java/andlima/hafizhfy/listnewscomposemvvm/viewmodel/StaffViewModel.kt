package andlima.hafizhfy.listnewscomposemvvm.viewmodel

import andlima.hafizhfy.listnewscomposemvvm.data.GetAllStaffResponseItem
import andlima.hafizhfy.listnewscomposemvvm.repository.StaffRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StaffViewModel @Inject constructor(private val api: StaffRepository): ViewModel() {
    private val staffState = MutableStateFlow(emptyList<GetAllStaffResponseItem>())
    val dataState : StateFlow<List<GetAllStaffResponseItem>>
        get() = staffState

    init {
        viewModelScope.launch {
            val staff = api.getAllStaff()
            staffState.value = staff
        }
    }
}