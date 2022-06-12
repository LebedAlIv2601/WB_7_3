package com.example.wb_7_3.presentation.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.wb_7_3.domain.model.CatModelDomain
import com.example.wb_7_3.domain.usecase.GetCatUseCase
import com.example.wb_7_3.domain.usecase.PostFavoriteCatUseCase
import com.example.wb_7_3.utils.Resource
import kotlinx.coroutines.Dispatchers

class SearchViewModel(
    private val getCatUseCase: GetCatUseCase,
    private val postFavoriteCatUseCase: PostFavoriteCatUseCase
) : ViewModel() {

    private val _currentCat = MutableLiveData<CatModelDomain>()
    val currentCat: LiveData<CatModelDomain>
        get() = _currentCat

    private val _loadingPermission = MutableLiveData<Boolean>()
    val loadingPermission: LiveData<Boolean>
        get() = _loadingPermission

    private val _enableToTouchLike = MutableLiveData<Boolean>()
    val enableToTouchLike: LiveData<Boolean>
        get() = _enableToTouchLike


    init {
        _loadingPermission.value = true
    }

    fun getCat() = liveData(Dispatchers.IO) {
        emit(Resource.Loading(data = null))
        try {
            Log.e("Loading", "Trying to load data from vm")
            emit(Resource.Success(data = getCatUseCase.execute()))
            Log.e("Loading", "Data loaded")
        } catch (e: Exception) {
            emit(Resource.Error(data = null, message = e.message ?: "Error Occurred!!!"))
        }
    }

    fun postCat() = liveData(Dispatchers.IO) {
        emit(Resource.Loading(data = null))
        try {
            Log.e("Loading", "Trying to load data from vm")
            emit(Resource.Success(data = currentCat.value?.let { postFavoriteCatUseCase.execute(it) }))
            Log.e("Loading", "Data loaded")
        } catch (e: Exception) {
            emit(Resource.Error(data = null, message = e.message ?: "Error Occurred!!!"))
        }
    }

    fun setCurrentCat(cat: CatModelDomain) {
        _currentCat.value = cat
    }

    fun setLoadingPermissionFalse() {
        _loadingPermission.value = false
    }

    fun setEnableTouchLike(b: Boolean){
        _enableToTouchLike.value = b
    }
}