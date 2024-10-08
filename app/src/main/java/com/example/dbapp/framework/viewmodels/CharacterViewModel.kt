package com.example.dbapp.framework.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dbapp.data.network.models.CharacterAPI
import com.example.dbapp.domain.GetCharacterByNameRequirement
import com.example.dbapp.domain.GetCharactersRequirement
import kotlinx.coroutines.launch

class CharacterViewModel : ViewModel() {
    val characters = MutableLiveData<List<CharacterAPI>>()
    private val getCharactersRequirement = GetCharactersRequirement()
    private val getCharacterByNameRequirement = GetCharacterByNameRequirement()

    fun loadAllCharacters(limit: Int) {
        viewModelScope.launch {
            val allCharacters = getCharactersRequirement(limit)
            Log.d("CharacterViewModel", "Total characters fetched: ${allCharacters.size}")
            characters.value = allCharacters
        }
    }

    fun searchCharacter(name: String) {
        viewModelScope.launch {
            try {
                val searchResults = getCharacterByNameRequirement(name)
                characters.value = searchResults
                Log.e ("CharaterViewModel", "Resultado: ${searchResults}")
            } catch (e: Exception) {
                Log.e("CharacterViewModel", "Error searching characters", e)
            }
        }
    }
}