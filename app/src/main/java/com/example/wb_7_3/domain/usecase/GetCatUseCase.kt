package com.example.wb_7_3.domain.usecase

import com.example.wb_7_3.domain.model.CatModelDomain
import com.example.wb_7_3.domain.repository.GetCatRepository
import javax.inject.Inject

class GetCatUseCase @Inject constructor(private val repository: GetCatRepository){

    suspend fun execute(): CatModelDomain{
        return repository.getCat()
    }

}