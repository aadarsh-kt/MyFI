package com.example.financetracker.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.financetracker.data.models.Amount
import com.example.financetracker.ui.screens.components.DetailsCard
import com.example.financetracker.ui.screens.components.DetailsScreen
import androidx.compose.ui.graphics.Color


@Composable
fun IncomeScreen(
    amounts: List<Amount>,
    onBackPressed: () -> Unit,
    onAddPressed: () -> Unit,
) {
    DetailsScreen(
        detailsName = "Income",
        detailsData = amounts,
        colors = { amount -> Color(amount.categoryColor) },
        amounts = { amount -> amount.categoryAmount.toDouble() },
        onBackPressed = onBackPressed,
        onAddPressed = onAddPressed
    ) {
        LazyColumn{
            items(amounts) { income ->
                DetailsCard(
                    detailsName = income.categoryName,
                    amount = income.categoryAmount.toDouble(),
                    color = Color(income.categoryColor)
                )
            }
        }

    }
}