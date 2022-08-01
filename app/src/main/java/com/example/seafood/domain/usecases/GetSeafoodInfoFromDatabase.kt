package com.example.seafood.domain.usecases

import com.example.seafood.core.models.Result
import com.example.seafood.data.repository.SeafoodRepository
import com.example.seafood.domain.model.FoodInfo
import javax.inject.Inject

class GetSeafoodInfoFromDatabase @Inject constructor(private val seafoodRepository: SeafoodRepository) {
    suspend operator fun invoke(): Result<List<FoodInfo>> =
        seafoodRepository.getSeafoodInfoFromDatabase()
}