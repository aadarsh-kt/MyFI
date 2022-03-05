package com.example.financetracker.data

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class FinanceApplication : Application() {

    private val applicationScope = CoroutineScope(SupervisorJob())

    private val database by lazy { FinanceDatabase.getDatabase(this, applicationScope) }
    val financeRepository by lazy { FinanceRepository(database.financeDao()) }
}