package com.template.app.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.template.app.data.model.SampleItem
import com.template.app.data.model.UiState
import com.template.app.di.AppContainer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {

    private val repository = AppContainer.sampleRepository

    private val _item = MutableStateFlow<UiState<SampleItem>>(UiState.Loading)
    val item: StateFlow<UiState<SampleItem>> = _item.asStateFlow()

    fun loadItem(itemId: Int) {
        viewModelScope.launch {
            _item.value = UiState.Loading
            try {
                val result = repository.getItemById(itemId)
                _item.value = UiState.Success(result)
            } catch (e: Exception) {
                _item.value = UiState.Error(
                    message = e.localizedMessage ?: "Failed to load item",
                    throwable = e
                )
            }
        }
    }
}
