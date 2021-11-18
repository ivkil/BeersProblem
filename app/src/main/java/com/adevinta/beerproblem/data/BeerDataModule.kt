package com.adevinta.beerproblem.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object BeerDataModule {
    @Provides
    fun provideJson(): Json = Json { ignoreUnknownKeys = true }

    @Provides fun provideRetrofit(json: Json): Retrofit = Retrofit.Builder()
        .baseUrl(PUNK_API_BASE_URL)
        .addConverterFactory(json.asConverterFactory(MediaType.get(PUNK_API_CONTENT_TYPE)))
        .build()

    @Provides fun provideBeerApi(retrofit: Retrofit): BeerApi = retrofit.create(BeerApi::class.java)

    private const val PUNK_API_BASE_URL = "https://api.punkapi.com/v2/"
    private const val PUNK_API_CONTENT_TYPE = "application/json; charset=utf-8"
}
