package ru.suleyman.learnkotlinapp.view

import com.arellomobile.mvp.MvpView
import ru.suleyman.domain.model.Hero

interface HeroListView: MvpView {

    fun presentHero(data: List<Hero>)
    fun presentLoading(loading: Boolean)
}