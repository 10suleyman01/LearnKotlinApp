package ru.suleyman.data.remote.provider

import kotlinx.coroutines.Deferred
import ru.suleyman.data.model.HeroModel
import ru.suleyman.data.remote.factory.RetrofitFactory

class HeroProvider {

    fun getHeroList(): Deferred<List<HeroModel>> {
        return RetrofitFactory.getHeroService().getHeroes()
    }

}