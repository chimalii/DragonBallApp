package com.example.dbapp.framework.views

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dbapp.data.network.models.CharacterAPI
import com.example.dbapp.databinding.ActivityMainBinding
import com.example.dbapp.framework.adapters.CharacterAdapter
import com.example.dbapp.framework.viewmodels.CharacterViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: CharacterAdapter
    private val viewModel: CharacterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initializeBinding()
        initializeObservers()
        initializeSearch()
        initializeBackButton()
        viewModel.loadAllCharacters(10)
    }

    private fun initializeBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun initializeObservers() {
        viewModel.characters.observe(this) { characterList ->
            updateRecyclerView(characterList as ArrayList<CharacterAPI>)
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

    private fun updateRecyclerView(dataForList: ArrayList<CharacterAPI>) {
        if (::adapter.isInitialized) {
            adapter.updateData(dataForList)
        } else {
            setUpRecyclerView(dataForList)
        }
    }

    private fun initializeSearch() {
        binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    viewModel.searchCharacter(it)
                }
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                // Opicional para que busque mientars escribe,
                // la coloco porque si no no funciona el object
                return false
            }
        })
    }

    private fun initializeBackButton() {
        binding.btnBack.setOnClickListener {
            binding.searchBar.setQuery("", false)
            viewModel.loadAllCharacters(10)
        }
    }
}
