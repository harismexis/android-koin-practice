package com.harismexis.koinpractice.presentation.screens.herodetail.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harismexis.koinpractice.datamodel.repository.HeroLocalRepository
import com.harismexis.koinpractice.framework.extensions.getErrorMessage
import com.harismexis.koinpractice.presentation.result.HeroDetailResult
import kotlinx.coroutines.launch

class HeroDetailViewModel(
    private val heroLocal: HeroLocalRepository,
) : ViewModel() {

    private val tag = HeroDetailViewModel::class.qualifiedName

    private val mHeroDetailResult = MutableLiveData<HeroDetailResult>()
    val heroDetailResult: LiveData<HeroDetailResult>
        get() = mHeroDetailResult

    fun getHeroById(itemId: Int) {
        viewModelScope.launch {
            try {
                val item = heroLocal.getHero(itemId)
                item?.let {
                    mHeroDetailResult.value = HeroDetailResult.Success(item)
                }
            } catch (e: Exception) {
                Log.d(tag, e.getErrorMessage())
                mHeroDetailResult.value = HeroDetailResult.Error(e.getErrorMessage())
            }
        }
    }

}
