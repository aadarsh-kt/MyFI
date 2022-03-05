package com.example.financetracker.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.financetracker.ui.screens.CreateScreen
import com.example.financetracker.ui.screens.ExpenditureScreen
import com.example.financetracker.ui.screens.HomeScreen
import com.example.financetracker.ui.screens.IncomeScreen
import com.example.financetracker.ui.theme.FinanceTrackerTheme
import com.example.financetracker.utils.FinanceScreen

class MainActivity : ComponentActivity() {

    private val mainViewModel by viewModels<MainViewModel>()

    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FinanceApp(mainViewModel)
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun FinanceApp(
    mainViewModel: MainViewModel
) {
    FinanceTrackerTheme {

        val navController = rememberNavController()
        val backstackEntry = navController.currentBackStackEntryAsState()

        FinanceNavHost(
            navController = navController,
            mainViewModel
        )
    }
}

//create NavHost
@ExperimentalAnimationApi
@Composable
fun FinanceNavHost(
    navController: NavHostController,
    mainViewModel: MainViewModel
) {
    //create variables for all screens
    val homeScreen = FinanceScreen.Home.name
    val incomeScreen = FinanceScreen.Income.name
    val expenditureScreen = FinanceScreen.Expenditure.name
    val createScreen = FinanceScreen.Create.name

    mainViewModel.loadIncomes()
    mainViewModel.loadExpenditure()

    //get data to the ui from mainViewModel
    val incomes by mainViewModel.allIncomes.collectAsState()
    val expenditures by mainViewModel.allExpenditure.collectAsState()

    var totalIncome = 0.0
    incomes.forEach {
        totalIncome += it.incomeAmount.toDouble()
    }

    var totalExpenditure = 0.0
    expenditures.forEach {
        totalExpenditure += it.expenditureAmount.toDouble()
    }

    val totalSavings = totalIncome - totalExpenditure

    NavHost(
        navController = navController,
        startDestination = FinanceScreen.Home.name,
    ) {

        composable(homeScreen) {
            HomeScreen(
                onItemClick = { screen -> navController.navigate(screen) },
                totalSavings = totalSavings.toString()
            )
        }
        composable(incomeScreen) {
            IncomeScreen(
                amounts = incomes,
                onBackPressed = {
                    navController.navigate(homeScreen) {
                        popUpTo(homeScreen)
                    }
                },
                onAddPressed = {
                    navController.navigate(createScreen)
                }
            )
        }
        composable(expenditureScreen) {
            ExpenditureScreen(
                expenditures = expenditures,
                onBackPressed = {
                    navController.navigate(homeScreen) {
                        popUpTo(homeScreen)
                    }
                },
                onAddPressed = {
                    navController.navigate(createScreen)
                }
            )
        }

        composable(createScreen) {
            CreateScreen(
                onBackPressed = {
                    navController.navigate(homeScreen) {
                        popUpTo(homeScreen)
                    }
                },
                onSubmit = { item ->
                    mainViewModel.submitIncome(item)
                    navController.navigate(incomeScreen)
                }
            )
        }

    }
}