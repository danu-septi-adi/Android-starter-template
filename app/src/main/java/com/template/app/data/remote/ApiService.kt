package com.template.app.data.remote

import com.template.app.data.model.SampleItem
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Retrofit API interface.
 * Adjust base URL and endpoints to match your backend.
 */
interface ApiService {

    @GET("posts")
    suspend fun getItems(
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 20
    ): List<SampleItem>

    @GET("posts/{id}")
    suspend fun getItemById(
        @Path("id") id: Int
    ): SampleItem
}
