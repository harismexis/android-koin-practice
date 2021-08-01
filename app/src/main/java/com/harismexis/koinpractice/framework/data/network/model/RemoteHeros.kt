package com.harismexis.koinpractice.framework.data.network.model

data class RemoteHeros(
    val info: RemoteInfo,
    val results: List<RemoteHero>,
)
