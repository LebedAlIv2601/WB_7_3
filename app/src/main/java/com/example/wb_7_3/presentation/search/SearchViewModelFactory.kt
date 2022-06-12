package com.example.wb_7_3.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wb_7_3.domain.usecase.GetCatUseCase
import com.example.wb_7_3.domain.usecase.PostFavoriteCatUseCase
import javax.inject.Inject

class SearchViewModelFactory @Inject constructor(
    private val getCatUseCase: GetCatUseCase,
    private val postFavoriteCatUseCase: PostFavoriteCatUseCase
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        require(modelClass == SearchViewModel::class.java)
        return SearchViewModel(getCatUseCase, postFavoriteCatUseCase) as T
    }
}