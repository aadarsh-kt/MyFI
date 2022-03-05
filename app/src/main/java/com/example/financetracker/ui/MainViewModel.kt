package com.example.financetracker.ui

import android.app.Application
import androidx.lifecycle.*
import com.example.financetracker.data.FinanceApplication
import com.example.financetracker.data.models.Expenditure
import com.example.financetracker.data.models.Income
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    //repository scoped to application
    private val repository = getApplication<FinanceApplication>().financeRepository

    private val _allIncomes = MutableStateFlow<List<Income>>(emptyList())
    val allIncomes : StateFlow<List<Income>>
        get() = _allIncomes

    private val _allExpenditure = MutableStateFlow<List<Expenditure>>(emptyList())
    val allExpenditure : StateFlow<List<Expenditure>>
        get() = _allExpenditure

    fun loadIncomes() = effect {
        repository.getAllIncomes().collect { incomeList ->
            _allIncomes.value = incomeList
        }
    }

    fun loadExpenditure() = effect {
        repository.getAllExpenditure().collect { expenditureList ->
            _allExpenditure.value = expenditureList
        }
    }

    fun submitIncome(income: Income) = effect {
        repository.insertIncome(income)
    }

    private fun effect(block: suspend () -> Unit) {
        viewModelScope.launch { block() }
    }
}