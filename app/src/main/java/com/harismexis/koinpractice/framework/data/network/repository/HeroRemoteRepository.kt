package com.harismexis.koinpractice.framework.data.network.repository

import com.harismexis.koinpractice.core.domain.Hero
import com.harismexis.koinpractice.core.repository.HeroRemote
import com.harismexis.koinpractice.framework.data.network.data.RickAndMortyRemoteDao
import com.harismexis.koinpractice.framework.util.extensions.hero.toItems

class HeroRemoteRepository(
    private val dao: RickAndMortyRemoteDao
): HeroRemote {
    override suspend fun getHeros(name: String?): List<Hero> = dao.getHeros(name).toItems()

}