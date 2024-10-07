package com.example.dbapp.domain

import com.example.dbapp.data.network.CharacterRepository
import com.example.dbapp.data.network.models.CharacterAPI

class GetCharactersRequirement {
    private val repository = CharacterRepository()

    suspend operator fun invoke(limit: Int): List<CharacterAPI> {
        return repository.getAllCharacters(limit)
    }
}