package com.harismexis.koinpractice.framework.datasource.database.repository

import com.harismexis.koinpractice.datamodel.domain.Hero
import com.harismexis.koinpractice.datamodel.repository.HeroLocal
import com.harismexis.koinpractice.framework.datasource.database.data.RickAndMortyLocalDao
import com.harismexis.koinpractice.framework.extensions.hero.toItem
import com.harismexis.koinpractice.framework.extensions.hero.toItems
import com.harismexis.koinpractice.framework.extensions.hero.toLocalItems

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