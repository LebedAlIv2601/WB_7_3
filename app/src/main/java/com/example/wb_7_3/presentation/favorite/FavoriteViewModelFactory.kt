package com.example.wb_7_3.presentation.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wb_7_3.domain.usecase.GetFavoriteCatsUseCase
import javax.inject.Inject

class FavoriteViewModelFactory @Inject constructor(
    private val getFavoriteCatsUseCase: GetFavoriteCatsUseCase
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        require(modelClass == FavoriteViewModel::class.java)
        return FavoriteViewModel(getFavoriteCatsUseCase) as T
    }
}