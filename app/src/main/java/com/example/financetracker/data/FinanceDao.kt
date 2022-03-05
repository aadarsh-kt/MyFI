package com.example.financetracker.data

import androidx.room.*
import androidx.room.Dao
import com.example.financetracker.data.models.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FinanceDao {

    //CRUD for expenditure
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIncome(income: Income)

    @Query("SELECT * FROM income_table ORDER BY incomeId DESC")
    fun getAllIncomes() : Flow<List<Income>>

//    @Query("SELECT * FROM finance_table WHERE categoryId = :id")
//    suspend fun getIncome(id: Long): Amount

//    @Update
//    suspend fun updateIncome(amount: Amount)
//
//    @Query("DELETE FROM finance_table WHERE categoryId = :id")
//    suspend fun deleteIncome(id: Long)

    //CRUD for expenditure
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExpenditure(expenditure: Expenditure)

    @Query("SELECT * FROM expenditure_table ORDER BY expenditureId DESC")
    fun getAllExpenditure() : Flow<List<Expenditure>>

    @Query("SELECT * FROM expenditure_table WHERE expenditureId = :id")
    suspend fun getExpenditure(id: Long): Expenditure
//
//    @Update
//    suspend fun updateExpenditure(expenditure: Expenditure)
//
//    @Query("DELETE FROM expenditure_table WHERE expenditureId = :id")
//    suspend fun deleteExpenditure(id: Long)



}