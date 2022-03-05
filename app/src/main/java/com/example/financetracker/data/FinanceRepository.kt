package com.example.financetracker.data

import com.example.financetracker.data.models.Expenditure
import com.example.financetracker.data.models.Income
import kotlinx.coroutines.flow.Flow

class FinanceRepository(private val financeDao: FinanceDao) {

    //Repository for income
    fun getAllIncomes(): Flow<List<Income>> = financeDao.getAllIncomes()

    suspend fun insertIncome(income: Income) {
        financeDao.insertIncome(income)
    }

    //Repository for Expenditure
    fun getAllExpenditure(): Flow<List<Expenditure>> = financeDao.getAllExpenditure()

    suspend fun insertExpenditure(expenditure: Expenditure) {
        financeDao.insertExpenditure(expenditure)
    }

//    suspend fun deleteExpenditure(id: Long) {
//        financeDao.deleteExpenditure(id)
//    }
//
//    //read word with particular id from database
//    suspend fun getExpenditure(id: Long): Expenditure {
//        return financeDao.getExpenditure(id)
//    }
//
//    suspend fun updateExpenditure(expenditure: Expenditure) {
//        return financeDao.updateExpenditure(expenditure)
//    }
}