package com.harismexis.koinpractice.datamodel.repository

import com.harismexis.koinpractice.datamodel.domain.Hero

interface HeroRemote {
    suspend fun getHeros(name: String? = null): List<Hero>

}