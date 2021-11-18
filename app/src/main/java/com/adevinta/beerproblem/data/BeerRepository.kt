package com.adevinta.beerproblem.data

import com.adevinta.beerproblem.domain.Beer
import javax.inject.Inject

class BeerRepository @Inject constructor(private val beerApi: BeerApi) {

    suspend fun getBeers(): Result<List<Beer>> = runCatching { beerApi.getBeers() }
}
