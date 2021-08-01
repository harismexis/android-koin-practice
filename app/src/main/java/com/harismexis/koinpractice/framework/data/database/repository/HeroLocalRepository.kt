package com.harismexis.koinpractice.framework.data.database.repository

import com.harismexis.koinpractice.core.domain.Hero
import com.harismexis.koinpractice.core.repository.HeroLocal
import com.harismexis.koinpractice.framework.data.database.data.RickAndMortyLocalDao
import com.harismexis.koinpractice.framework.util.extensions.hero.toItem
import com.harismexis.koinpractice.framework.util.extensions.hero.toItems
import com.harismexis.koinpractice.framework.util.extensions.hero.toLocalItems

class HeroLocalRepository (
    private val dao: RickAndMortyLocalDao
): HeroLocal {
    override suspend fun updateHeros(items: List<Hero>) {
        dao.deleteAllHeros()
        dao.insertHeros(items.toLocalItems())
    }

    override suspend fun getHero(itemId: Int): Hero? {
        val localItem = dao.getHeroById(itemId)
        localItem?.let {
            return it.toItem()
        }
        return null
    }

    override suspend fun getHeros(): List<Hero> {
        return dao.getAllHeros().toItems()
    }

}