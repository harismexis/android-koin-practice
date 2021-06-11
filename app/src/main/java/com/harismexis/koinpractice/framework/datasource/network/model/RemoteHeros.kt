package com.harismexis.koinpractice.framework.datasource.network.model

data class RemoteHeros(
    val info: RemoteInfo,
    val results: List<RemoteHero>,
)
