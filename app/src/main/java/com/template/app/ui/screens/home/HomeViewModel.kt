package com.template.app.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.template.app.data.model.SampleItem
import com.template.app.data.model.UiState
import com.template.app.di.AppContainer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class HomeUiState(
    val items: UiState<List<SampleItem>> = UiState.Loading,
    val currentPage: Int = 1
)

class HomeViewModel : ViewModel() {

    private val repository = AppContainer.sampleRepository

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadItems()
    }

    fun loadItems() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(items = UiState.Loading)
            try {
                val items = repository.getItems(page = _uiState.value.currentPage)
                _uiState.value = _uiState.value.copy(items = UiState.Success(items))
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    items = UiState.Error(
                        message = e.localizedMessage ?: "Unknown error",
                        throwable = e
                    )
                )
            }
        }
    }

    fun refresh() {
        _uiState.value = _uiState.value.copy(currentPage = 1)
        loadItems()
    }
}
