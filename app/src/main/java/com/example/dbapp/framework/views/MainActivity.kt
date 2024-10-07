package com.example.dbapp.framework.views

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dbapp.data.network.models.CharacterAPI
import com.example.dbapp.databinding.ActivityMainBinding
import com.example.dbapp.framework.adapters.CharacterAdapter
import com.example.dbapp.framework.viewmodels.CharacterViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: CharacterAdapter
    private lateinit var data: ArrayList<CharacterAPI>

    private val viewModel: CharacterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initializeBinding()
        initializeObservers()
        viewModel.loadAllCharacters(10)
    }

    private fun initializeBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun initializeObservers() {
        viewModel.characters.observe(this) { characterList ->
            setUpRecyclerView(characterList as ArrayList<CharacterAPI>)
        }
    }

    private fun setUpRecyclerView(dataForList: ArrayList<CharacterAPI>) {
        binding.RVCharacters.setHasFixedSize(true)
        val linearLayoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
        binding.RVCharacters.layoutManager = linearLayoutManager
        adapter = CharacterAdapter(dataForList)
        binding.RVCharacters.adapter = adapter
    }
}