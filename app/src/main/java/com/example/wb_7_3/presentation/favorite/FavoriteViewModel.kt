package com.example.wb_7_3.presentation.favorite

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.wb_7_3.domain.model.CatModelDomain
import com.example.wb_7_3.domain.usecase.GetFavoriteCatsUseCase
import com.example.wb_7_3.utils.Resource
import kotlinx.coroutines.Dispatchers

class FavoriteViewModel (private val getFavoriteCatsUseCase: GetFavoriteCatsUseCase): ViewModel(){

    private var _favoriteCatsList = MutableLiveData<List<CatModelDomain>>()
    val favoriteCatsList: LiveData<List<CatModelDomain>>
        get() = _favoriteCatsList

    private val _loadingPermission = MutableLiveData<Boolean>()
    val loadingPermission: LiveData<Boolean>
        get() = _loadingPermission


    init {
        _loadingPermission.value = true
    }

    fun getFavoriteCats() = liveData(Dispatchers.IO) {
        emit(Resource.Loading(data = null))
        try {
            Log.e("Loading", "Trying to load data from vm")
            emit(Resource.Success(data = getFavoriteCatsUseCase.execute()))
            Log.e("Loading", "Data loaded")
        } catch (e: Exception) {
            emit(Resource.Error(data = null, message = e.message ?: "Error Occurred!!!"))
        }
    }

    fun setFavoriteCatsList(list: List<CatModelDomain>){
        _favoriteCatsList.value = list
    }
    fun setLoadingPermissionFalse() {
        _loadingPermission.value = false
    }


}