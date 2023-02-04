package com.example.movietop.ui.movies.details.actors

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.movietop.R
import com.example.movietop.databinding.ItemActorBinding
import com.example.movietop.domain.movies.details.actors.Actor

class ActorViewHolder(
    private val binding: ItemActorBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(actor: Actor) {
        with(binding) {
            actor.posterUrl?.let { url ->
                ivActorPoster.load(url)
            } ?: run { ivActorPoster.setImageResource(R.drawable.defaul_actor_img) }

            actor.nameRu?.let { name ->
                tvActorName.text = name
            } ?: run { tvActorName.text = actor.nameEn }

            actor.description?.let { role ->
                tvActorRole.text = role
            }
        }
    }
}