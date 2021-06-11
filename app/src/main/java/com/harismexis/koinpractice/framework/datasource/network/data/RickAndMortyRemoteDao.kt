package com.harismexis.koinpractice.framework.datasource.network.data

import com.harismexis.koinpractice.framework.datasource.network.api.RickAndMortyApi
import com.harismexis.koinpractice.framework.datasource.network.model.RemoteHeros

class RickAndMortyRemoteDao(val api: RickAndMortyApi) {

    suspend fun getHeros(name: String? = null): RemoteHeros?? {
        return api.getHeros(name)
    }

}