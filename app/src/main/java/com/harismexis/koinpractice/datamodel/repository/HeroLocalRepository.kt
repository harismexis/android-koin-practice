package com.harismexis.koinpractice.datamodel.repository

import com.harismexis.koinpractice.datamodel.domain.Hero
import com.harismexis.koinpractice.framework.datasource.database.data.RickAndMortyLocalDao
import com.harismexis.koinpractice.framework.extensions.hero.toItem
import com.harismexis.koinpractice.framework.extensions.hero.toItems
import com.harismexis.koinpractice.framework.extensions.hero.toLocalItems

class HeroLocalRepository (
    private val dao: RickAndMortyLocalDao
) {
    suspend fun updateHeros(items: List<Hero>) {
        dao.deleteAllHeros()
        dao.insertHeros(items.toLocalItems())
    }

    suspend fun getHero(itemId: Int): Hero? {
        val localItem = dao.getHeroById(itemId)
        localItem?.let {
            return it.toItem()
        }
        return null
    }

    suspend fun getHeros(): List<Hero> {
        return dao.getAllHeros().toItems()
    }

}