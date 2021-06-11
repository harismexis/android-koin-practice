package com.harismexis.koinpractice.datamodel.repository

import com.harismexis.koinpractice.datamodel.domain.Hero
import com.harismexis.koinpractice.framework.datasource.network.data.RickAndMortyRemoteDao
import com.harismexis.koinpractice.framework.extensions.hero.toItems

data class HeroRemoteRepository(
    val dao: RickAndMortyRemoteDao
) {
    suspend fun getHeros(name: String? = null): List<Hero> = dao.getHeros(name).toItems()

}