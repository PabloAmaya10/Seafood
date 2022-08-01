package com.example.seafood.domain.usecases

import com.example.seafood.core.models.Error
import com.example.seafood.core.models.Result
import com.example.seafood.core.models.SuccessResult
import javax.inject.Inject

class GetAccountUseCase @Inject constructor() {
    companion object {
        const val ERROR_CODE = "Error_GetAccountUseCase"
    }

    operator fun invoke(dataBillList: MutableMap<String, List<Int>>): Result<Int> {
        return try {
            var account = 0
            dataBillList.forEach { (_, list) ->
                account += list.first() * list.last()
            }
            SuccessResult(account)
        } catch (e: Exception) {
            Error(ERROR_CODE, "Unable to get extra collection list", emptyList(), e)
        }

    }
}