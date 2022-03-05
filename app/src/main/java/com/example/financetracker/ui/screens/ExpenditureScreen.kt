package com.example.financetracker.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.financetracker.data.models.Expenditure
import com.example.financetracker.ui.screens.components.DetailsCard
import com.example.financetracker.ui.screens.components.DetailsScreen

@Composable
fun ExpenditureScreen(
    expenditures: List<Expenditure>,
    onBackPressed: () -> Unit,
    onAddPressed: () -> Unit
) {

    DetailsScreen(
        detailsName = "Expenditure",
        detailsData = expenditures,
        colors = { expenditure -> Color(expenditure.expenditureColor) },
        amounts = { expenditure -> expenditure.expenditureAmount.toDouble() },
        onBackPressed = onBackPressed,
        onAddPressed = {

        }
    ) {

        LazyColumn{
            items(expenditures) { expenditure ->
                DetailsCard(
                    detailsName = expenditure.expenditureName,
                    amount = expenditure.expenditureAmount.toDouble(),
                    color = Color(expenditure.expenditureColor)
                )
            }
        }
    }
}