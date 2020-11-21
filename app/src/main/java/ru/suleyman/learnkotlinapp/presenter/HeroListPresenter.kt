package ru.suleyman.learnkotlinapp.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.suleyman.data.remote.provider.HeroProvider
import ru.suleyman.domain.converter.HeroConverter
import ru.suleyman.domain.repository.implementation.HeroRepository
import ru.suleyman.learnkotlinapp.view.HeroListView
import java.lang.Exception

@InjectViewState
class HeroListPresenter: MvpPresenter<HeroListView>() {

    private var heroRepository = HeroRepository(heroProvider = HeroProvider(), heroConverter =
                                                    HeroConverter()
    )

    fun fetchHeroes() {
        viewState.presentLoading(true)

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val heroes = heroRepository.fetchHeroes().await()
                withContext(Dispatchers.Main) {
                    viewState.presentLoading(false)
                    viewState.presentHero(heroes)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}