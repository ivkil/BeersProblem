package com.adevinta.beerproblem.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.adevinta.beerproblem.databinding.ItemBeerBinding
import com.adevinta.beerproblem.domain.Beer
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@FragmentScoped class BeersAdapter @Inject constructor(diffCallback: BeerDiffCallback) :
    ListAdapter<Beer, BeersAdapter.ViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemBeerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBindItem(getItem(position))
    }

    class ViewHolder(private val binding: ItemBeerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBindItem(beer: Beer) {
            binding.beerTitle.text = beer.name
            binding.beerTagline.text = beer.tagline
            binding.beerImage.load(beer.imageUrl)
        }
    }
}
