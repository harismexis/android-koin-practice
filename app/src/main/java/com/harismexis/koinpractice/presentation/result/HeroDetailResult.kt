package com.harismexis.koinpractice.presentation.result

import com.harismexis.koinpractice.datamodel.domain.Hero

sealed class HeroDetailResult {
    data class Success(val item: Hero): HeroDetailResult()
    data class Error(val error: String): HeroDetailResult()
}