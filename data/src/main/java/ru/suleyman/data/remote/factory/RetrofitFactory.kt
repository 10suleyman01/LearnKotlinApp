package ru.suleyman.data.remote.factory

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.create
import ru.suleyman.data.remote.service.HeroService

class RetrofitFactory {

    companion object {
        val baseUrl = "https://api.opendota.com/api/"

        private fun getOkHttpInstance(): OkHttpClient {
            var loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            return OkHttpClient.Builder().
                    addInterceptor(loggingInterceptor).
                    build()
        }

        private fun getRetrofitClient(): Retrofit {

            val contentType = "application/json".toMediaType()

            return Retrofit.Builder().
                    baseUrl(baseUrl).
                    client(getOkHttpInstance()).
                    addConverterFactory(Json.asConverterFactory(contentType)).
                    addCallAdapterFactory(CoroutineCallAdapterFactory()).
                    build()
        }

        fun getHeroService() = RetrofitFactory.getRetrofitClient().create(HeroService::class.java)
    }
}