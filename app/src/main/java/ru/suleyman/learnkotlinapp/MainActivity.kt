package ru.suleyman.learnkotlinapp

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import ru.suleyman.learnkotlinapp.adapter.HeroAdapter
import ru.suleyman.learnkotlinapp.presenter.HeroListPresenter
import ru.suleyman.learnkotlinapp.view.HeroListView

class MainActivity : MvpAppCompatActivity(), HeroListView{

    private var mAdapter = HeroAdapter()
    private lateinit var recyclerHeroList: RecyclerView
    private lateinit var tvLoading: TextView

    @InjectPresenter
    lateinit var heroListPresenter: HeroListPresenter;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerHeroList = findViewById(R.id.recyclerHeroList)
        tvLoading = findViewById(R.id.tvLoading)
        setupAdapter()
        heroListPresenter.fetchHeroes()
    }


    fun setupAdapter() {
        val layoutManager = LinearLayoutManager(this)
        recyclerHeroList.layoutManager = layoutManager
        recyclerHeroList.adapter = mAdapter
    }

    override fun presentHero(data: List<ru.suleyman.domain.model.Hero>) {
        mAdapter.setData(data)
    }

    override fun presentLoading(loading: Boolean) {
        if (loading) tvLoading.visibility = View.VISIBLE
        else tvLoading.visibility = View.GONE
    }
}