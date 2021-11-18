package com.adevinta.beerproblem.presentation

import androidx.recyclerview.widget.DiffUtil
import com.adevinta.beerproblem.domain.Beer
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@FragmentScoped class BeerDiffCallback @Inject constructor() : DiffUtil.ItemCallback<Beer>() {

    override fun areItemsTheSame(oldItem: Beer, newItem: Beer) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Beer, newItem: Beer) = oldItem == newItem
}
