package com.harismexis.koinpractice.framework.util.resource

import android.content.Context
import androidx.annotation.StringRes
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ResourceProvider @Inject constructor(@ApplicationContext private val appContext: Context) {

    fun getString(@StringRes stringId: Int): String {
        return appContext.getString(stringId)
    }

}