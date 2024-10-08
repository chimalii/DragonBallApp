package com.example.dbapp.data.network

import com.example.dbapp.data.network.models.CharacterAPI

class CharacterRepository {
    private val apiClient = DBApiClient()

    suspend fun getAllCharacters(limit: Int): List<CharacterAPI> {
        return apiClient.getAllCharacters(limit)
    }

    suspend fun getCharacterByName(name: String): List<CharacterAPI> {
        return apiClient.getCharacterByName(name)
    }
}