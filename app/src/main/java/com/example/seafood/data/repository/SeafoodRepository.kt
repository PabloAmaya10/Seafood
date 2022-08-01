package com.example.seafood.data.repository

import com.example.seafood.core.models.SuccessResult
import com.example.seafood.core.models.Result
import com.example.seafood.core.models.Error
import com.example.seafood.data.database.dao.SeafoodDao
import com.example.seafood.data.database.entities.toEntity
import com.example.seafood.data.network.SeafoodService
import com.example.seafood.domain.model.FoodInfo
import com.example.seafood.domain.model.toModel
import javax.inject.Inject

class SeafoodRepository @Inject constructor(
    private val seafoodService: SeafoodService,
    private val seafoodDao: SeafoodDao
) {
    companion object {
        const val ERROR_CODE = "Error_GetSeafoodInformationUseCase"
    }

    suspend fun getSeafoodInformation(): Result<List<FoodInfo>> {
        return try {
            val foodList: MutableList<FoodInfo> = mutableListOf()
            when (val response = seafoodService.getSeafoodService()) {
                is SuccessResult -> {
                    foodList.addAll(response.result.map { it.toModel() })
                    seafoodDao.deleteAllSeafood()
                    seafoodDao.insertAll(foodList.map { it.toEntity() })
                    SuccessResult(foodList)
                }
                is Error -> {
                    Error(
                        response.code,
                        "Unable to get extra collection list",
                        emptyList(),
                        response.exception
                    )

                }
                else -> SuccessResult(emptyList())
            }
        } catch (e: Exception) {
            Error(ERROR_CODE, "Unable to get extra collection list", emptyList(), e)
        }
    }

    suspend fun getSeafoodInfoFromDatabase(): Result<List<FoodInfo>> {
        return try {
            SuccessResult(seafoodDao.getAllSeafood().map { it.toModel() })

        } catch (e: Exception) {
            Error(ERROR_CODE, "Unable to get extra collection list", emptyList(), e)
        }
    }
}