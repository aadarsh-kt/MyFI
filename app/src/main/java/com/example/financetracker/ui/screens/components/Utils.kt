package com.example.financetracker.ui.screens.components

fun <E> List<E>.extractProportions(selector: (E) -> Double) : List<Float> {
    //here this is the list and it is the individual element in it.
    //sumOf is like map
    val total = this.sumOf { selector(it) }
    //returns a list of proportions.
    return this.map { (selector(it) / total).toFloat() }
}