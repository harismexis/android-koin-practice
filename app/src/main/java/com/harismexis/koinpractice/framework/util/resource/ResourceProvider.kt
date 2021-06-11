package com.harismexis.koinpractice.framework.util.resource

import android.content.Context
import androidx.annotation.StringRes

class ResourceProvider (private val appContext: Context) {

    fun getString(@StringRes stringId: Int): String {
        return appContext.getString(stringId)
    }

}