package ru.suleyman.data.remote.service

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import ru.suleyman.data.model.HeroModel

interface HeroService {

    @GET("./heroes")
    fun getHeroes(): Deferred<List<HeroModel>>
}