package ru.suleyman.domain.converter

import ru.suleyman.data.model.HeroModel
import ru.suleyman.domain.model.Hero

class HeroConverter {

    fun from(model: HeroModel): Hero {
        return Hero(model.id, model.name, model.attack_type)
    }
}