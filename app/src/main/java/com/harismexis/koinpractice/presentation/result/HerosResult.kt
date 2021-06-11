package com.harismexis.koinpractice.presentation.result

import com.harismexis.koinpractice.datamodel.domain.Hero

sealed class HerosResult {
    data class Success(val items: List<Hero>): HerosResult()
    data class Error(val error: Exception): HerosResult()
}