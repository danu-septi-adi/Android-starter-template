package com.template.app.data.model

import kotlinx.serialization.Serializable

/**
 * Sample data model demonstrating Kotlin Serialization.
 * Replace with your actual API response models.
 */
@Serializable
data class SampleItem(
    val id: Int = 0,
    val title: String = "",
    val body: String = "",
    val userId: Int = 0
)

// Example: JSON response wrapper
@Serializable
data class ApiResponse<T>(
    val success: Boolean = false,
    val message: String = "",
    val data: T? = null
)

// Example: paginated response
@Serializable
data class PaginatedResponse<T>(
    val items: List<T> = emptyList(),
    val page: Int = 1,
    val totalPages: Int = 1,
    val totalItems: Int = 0
)
