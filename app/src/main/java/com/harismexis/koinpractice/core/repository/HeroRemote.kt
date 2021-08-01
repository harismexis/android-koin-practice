package com.harismexis.koinpractice.core.repository

import com.harismexis.koinpractice.core.domain.Hero

interface HeroRemote {
    suspend fun getHeros(name: String? = null): List<Hero>

}