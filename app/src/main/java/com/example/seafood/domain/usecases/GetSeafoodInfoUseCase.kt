package com.example.seafood.domain.usecases

import com.example.seafood.data.repository.SeafoodRepository
import com.example.seafood.domain.model.FoodInfo
import javax.inject.Inject
import com.example.seafood.core.models.Result

class GetSeafoodInfoUseCase @Inject constructor(private val seafoodRepository: SeafoodRepository) {

    suspend operator fun invoke(): Result<List<FoodInfo>> =
        seafoodRepository.getSeafoodInformation()
}