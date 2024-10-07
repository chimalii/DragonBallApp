package com.example.dbapp.data.network

import com.example.dbapp.data.network.models.CharacterAPI
import com.example.dbapp.data.network.models.PaginationResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface DBApiService {
    @GET("characters")
    suspend fun getCharacters(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): PaginationResponse<CharacterAPI>
}