package andlima.hafizhfy.listnewscomposemvvm.viewmodel

import andlima.hafizhfy.listnewscomposemvvm.data.GetAllNewsResponseItem
import andlima.hafizhfy.listnewscomposemvvm.repository.NewsRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val api: NewsRepository) : ViewModel() {
    private val newsState = MutableStateFlow(emptyList<GetAllNewsResponseItem>())
    val dataState : StateFlow<List<GetAllNewsResponseItem>>
        get() = newsState

    init {
        viewModelScope.launch {
            val news = api.getAllNews()
            newsState.value = news
        }
    }
}