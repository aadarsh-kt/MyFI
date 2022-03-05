package com.example.financetracker.data.models

import androidx.compose.ui.graphics.Color
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "expenditure_table")
data class Expenditure(
    @PrimaryKey(autoGenerate = true) val expenditureId: Long,
    @ColumnInfo(name = "name") val expenditureName: String,
    @ColumnInfo(name = "amount") val expenditureAmount: String,
    @ColumnInfo(name = "color") val expenditureColor: Long
) {
    constructor(expenditureName: String, expenditureAmount: String, expenditureColor: Long) : this(0L, expenditureName, expenditureAmount, expenditureColor)

}
