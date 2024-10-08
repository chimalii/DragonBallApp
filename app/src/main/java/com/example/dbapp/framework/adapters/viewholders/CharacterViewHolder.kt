package com.example.dbapp.framework.adapters.viewholders

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.dbapp.R
import com.example.dbapp.data.network.models.CharacterAPI
import com.example.dbapp.databinding.ItemCharacterBinding

class CharacterViewHolder(
    private val binding: ItemCharacterBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(character: CharacterAPI) {
        val context = binding.root.context

        binding.tvName.text = character.name
        binding.tvDescription.text = character.description
        binding.tvKi.text = context.getString(R.string.ki, character.ki)
        binding.tvMaxKi.text = context.getString(R.string.max_ki, character.maxKi)
        binding.tvRace.text = context.getString(R.string.raza, character.race)
        binding.tvGender.text = context.getString(R.string.genero, character.gender)
        binding.tvAffiliation.text = context.getString(R.string.afiliacion, character.affiliation)

        // Siempre mostrar la imagen
        binding.ivImage.visibility = View.VISIBLE
        loadImage(character.image, binding.ivImage)

        binding.btnToggleDetails.setOnClickListener {
            if (binding.llDetails.visibility == View.GONE) {
                binding.llDetails.visibility = View.VISIBLE
                binding.btnToggleDetails.text = context.getString(R.string.ocultar_detalles)
            } else {
                binding.llDetails.visibility = View.GONE
                binding.btnToggleDetails.text = context.getString(R.string.mostrar_detalles)
            }
        }
    }

    private fun loadImage(url: String, imageView: ImageView) {
        val requestOptions = RequestOptions()
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .fitCenter()
            .priority(Priority.HIGH)

        Glide.with(itemView.context)
            .load(url)
            .apply(requestOptions)
            .into(imageView)
    }
}