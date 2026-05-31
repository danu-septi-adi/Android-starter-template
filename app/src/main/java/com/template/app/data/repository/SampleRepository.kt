package com.template.app.data.repository

import com.template.app.data.model.SampleItem
import com.template.app.data.remote.RetrofitInstance

/**
 * Repository layer — single source of truth.
 * Add caching, offline support, or data-source switching here.
 */
class SampleRepository {

    private val api = RetrofitInstance.apiService

    suspend fun getItems(page: Int = 1): List<SampleItem> {
        return api.getItems(page = page, limit = 20)
    }

    suspend fun getItemById(id: Int): SampleItem {
        return api.getItemById(id)
    }
}
