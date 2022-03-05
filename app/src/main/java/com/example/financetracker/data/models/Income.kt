package com.example.financetracker.data.models

import androidx.room.*

@Entity(tableName = "income_table")
data class Income(
    @PrimaryKey(autoGenerate = true) val incomeId: Long,
    @ColumnInfo(name = "name") val incomeName: String,
    @ColumnInfo(name = "amount") val incomeAmount: String,
    @ColumnInfo(name = "color") val incomeColor: Long
) {
    constructor(incomeName: String, incomeAmount: String, incomeColor: Long) : this(0L, incomeName, incomeAmount, incomeColor)

}