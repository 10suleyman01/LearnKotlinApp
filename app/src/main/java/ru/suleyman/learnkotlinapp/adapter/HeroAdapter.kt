package ru.suleyman.learnkotlinapp.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import ru.suleyman.domain.model.Hero
import ru.suleyman.learnkotlinapp.R
import java.util.*

class HeroAdapter: RecyclerView.Adapter<HeroAdapter.HeroHolder>() {

    private var mHeroList: MutableList<Hero> = LinkedList()

    fun setData(newHeroList: List<Hero>) {
        mHeroList.clear()
        mHeroList.addAll(newHeroList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroHolder {
        return HeroHolder(LayoutInflater.from(parent.context).inflate(R.layout.cell_hero, parent, false))
    }

    override fun getItemCount() = mHeroList.count()

    override fun onBindViewHolder(holder: HeroHolder, position: Int) {
        holder.bind(mHeroList[position])
    }

    class HeroHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvHeroTitle = itemView.findViewById<TextView>(R.id.tvHeroTitle)
        private val tvAttackType = itemView.findViewById<TextView>(R.id.tvAttackType)
        private val imageAvatar = itemView.findViewById<ImageView>(R.id.imageAvatar)


        fun bind(model: Hero) {
            tvHeroTitle.text = model.title
            tvAttackType.text = model.attackType
        }
    }
}
