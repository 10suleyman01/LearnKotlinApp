package ru.suleyman.domain.repository.implementation

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import ru.suleyman.data.remote.provider.HeroProvider
import ru.suleyman.domain.converter.HeroConverter
import ru.suleyman.domain.model.Hero

class HeroRepository(val heroProvider: HeroProvider, val heroConverter: HeroConverter) {

    suspend fun fetchHeroes(): Deferred<List<Hero>> {
        return try {
            val heroes = heroProvider.getHeroList().await()
            GlobalScope.async {
                heroes.map {
                    heroModel -> heroConverter.from(heroModel)
                }
            }
        } catch (e: Exception) {
            GlobalScope.async { error(e) }
        }
    }

}