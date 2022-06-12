package com.example.wb_7_3.domain.usecase

import com.example.wb_7_3.domain.model.CatModelDomain
import com.example.wb_7_3.domain.repository.PostFavoriteCatRepository
import javax.inject.Inject

class PostFavoriteCatUseCase @Inject constructor(private val repository: PostFavoriteCatRepository) {

    suspend fun execute(cat: CatModelDomain): Boolean{
        return repository.postCat(cat)
    }
}