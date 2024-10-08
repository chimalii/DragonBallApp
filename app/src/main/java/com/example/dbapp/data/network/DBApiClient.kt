package com.example.dbapp.data.network

import com.example.dbapp.data.network.models.CharacterAPI

class DBApiClient {
    private val api: DBApiService = NetworkModuleDI()

    suspend fun getAllCharacters(limit: Int): List<CharacterAPI> {
        val allCharacters = mutableListOf<CharacterAPI>()
        var page = 1
        var totalPages: Int

        do {
            val response = api.getCharacters(page, limit)
            totalPages = response.meta.totalPages
            allCharacters.addAll(response.items)
            page++
        } while (page <= totalPages)

        return allCharacters
    }

    suspend fun getCharacterByName(name: String): List<CharacterAPI> {
        return try {
            api.getCharacterByName(name)
        } catch (e: Exception) {
            emptyList()
        }
    }
}