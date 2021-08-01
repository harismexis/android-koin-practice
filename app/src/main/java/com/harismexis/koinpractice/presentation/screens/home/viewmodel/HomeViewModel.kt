package com.harismexis.koinpractice.presentation.screens.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harismexis.koinpractice.core.repository.HeroLocal
import com.harismexis.koinpractice.core.repository.HeroRemote
import com.harismexis.koinpractice.framework.util.event.Event
import com.harismexis.koinpractice.framework.util.extensions.getErrorMessage
import com.harismexis.koinpractice.presentation.result.HerosResult
import kotlinx.coroutines.launch

class HomeViewModel(
    private val heroRemote: HeroRemote,
    private val heroLocal: HeroLocal
) : ViewModel() {

    private val TAG = HomeViewModel::class.qualifiedName

    private val mHerosResult = MutableLiveData<HerosResult>()
    val herosResult: LiveData<HerosResult>
        get() = mHerosResult

    private val mShowErrorMessage = MutableLiveData<Event<String>>()
    val showErrorMessage : LiveData<Event<String>>
        get() = mShowErrorMessage

    private var searchQuery: String? = null

    fun fetchHeros() {
        fetchRemoteHeros(searchQuery)
    }

    fun updateSearchQuery(query: String?) {
        searchQuery = query
        fetchRemoteHeros(query)
    }

    private fun fetchRemoteHeros(name: String? = null) {
        viewModelScope.launch {
            try {
                val items = heroRemote.getHeros(name)
                mHerosResult.value = HerosResult.Success(items)
                heroLocal.updateHeros(items)
            } catch (e: Exception) {
                Log.d(TAG, e.getErrorMessage())
                mHerosResult.value = HerosResult.Error(e)
                mShowErrorMessage.value = Event(e.getErrorMessage())
            }
        }
    }

}