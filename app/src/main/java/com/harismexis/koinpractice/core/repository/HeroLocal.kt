package com.harismexis.koinpractice.core.repository

import com.harismexis.koinpractice.core.domain.Hero

interface HeroLocal {
    suspend fun updateHeros(items: List<Hero>)

    suspend fun getHero(itemId: Int): Hero?

    suspend fun getHeros(): List<Hero>

}