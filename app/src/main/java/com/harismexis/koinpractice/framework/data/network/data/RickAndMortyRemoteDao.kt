package com.harismexis.koinpractice.framework.data.network.data

import com.harismexis.koinpractice.framework.data.network.api.RickAndMortyApi
import com.harismexis.koinpractice.framework.data.network.model.RemoteHeros

class RickAndMortyRemoteDao(val api: RickAndMortyApi) {

    suspend fun getHeros(name: String? = null): RemoteHeros?? {
        return api.getHeros(name)
    }

}