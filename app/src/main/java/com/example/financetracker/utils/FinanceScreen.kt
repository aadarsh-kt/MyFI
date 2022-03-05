package com.example.financetracker.utils

enum class FinanceScreen(
) {
    Home, Income, Expenditure, Create;

    companion object {
        fun fromRoute(route: String?): FinanceScreen =
            when (route?.substringBefore("/")) {
                Home.name -> Home
                Income.name -> Income
                Expenditure.name -> Expenditure
                Create.name -> Create
                null -> Home
                else -> throw IllegalArgumentException("Route $route is not a recognized route")
            }
    }
}