package com.example.seafood.ui.viewmodel

import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seafood.R
import com.example.seafood.core.models.SuccessResult
import com.example.seafood.core.models.Error
import com.example.seafood.domain.model.FoodInfo
import com.example.seafood.domain.usecases.GetAccountUseCase
import com.example.seafood.domain.usecases.GetSeafoodInfoFromDatabase
import com.example.seafood.domain.usecases.GetSeafoodInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@Suppress("UNNECESSARY_NOT_NULL_ASSERTION")
@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val getSeafoodInfoUseCase: GetSeafoodInfoUseCase,
    private val getAccountUseCase: GetAccountUseCase,
    private val getSeafoodInfoFromDatabase: GetSeafoodInfoFromDatabase
) :
    ViewModel() {
    private val _foodInfo = MutableLiveData<List<FoodInfo>>()
    private val _foodAccount = MutableLiveData<Int>()
    private val _error = MutableLiveData<Error<*>>()
    val foodInfo: LiveData<List<FoodInfo>> get() = _foodInfo
    val foodAccount: LiveData<Int> get() = _foodAccount
    val error: LiveData<Error<*>> get() = _error
    fun loadFoodInfo() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = getSeafoodInfoUseCase.invoke()) {
                is Error -> {
                    _error.postValue(result)
                }
                is SuccessResult -> {
                    _foodInfo.postValue(result.result!!)
                }
            }
        }
    }

    fun getAccount(dataBillList: MutableMap<String, List<Int>>) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = getAccountUseCase.invoke(dataBillList)) {
                is Error -> {
                    _error.postValue(result)
                }
                is SuccessResult -> {
                    _foodAccount.postValue(result.result!!)
                }
            }
        }

    }

    fun getFoodInfo() {

        viewModelScope.launch(Dispatchers.IO) {
            when (val result =getSeafoodInfoFromDatabase.invoke() ) {
                is Error -> {
                    _error.postValue(result)
                }
                is SuccessResult -> {
                    _foodInfo.postValue(result.result!!)
                }
            }
        }
    }

}