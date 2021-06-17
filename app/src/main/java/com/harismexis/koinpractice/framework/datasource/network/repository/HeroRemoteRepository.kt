package com.harismexis.koinpractice.framework.datasource.network.repository

import com.harismexis.koinpractice.datamodel.domain.Hero
import com.harismexis.koinpractice.datamodel.repository.HeroRemote
import com.harismexis.koinpractice.framework.datasource.network.data.RickAndMortyRemoteDao
import com.harismexis.koinpractice.framework.extensions.hero.toItems

class HeroRemoteRepository(
    val dao: RickAndMortyRemoteDao
): HeroRemote {
    override suspend fun getHeros(name: String?): List<Hero> = dao.getHeros(name).toItems()

}