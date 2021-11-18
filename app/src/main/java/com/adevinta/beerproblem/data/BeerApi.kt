package com.adevinta.beerproblem.data

import com.adevinta.beerproblem.domain.Beer
import retrofit2.http.GET

interface BeerApi {

    @GET("beers") suspend fun getBeers(): List<Beer>
}
