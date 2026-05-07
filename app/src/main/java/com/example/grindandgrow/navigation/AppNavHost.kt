package com.example.grindandgrow.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.grindandgrow.models.MainViewModel
import com.example.grindandgrow.screens.BudgetScreen
import com.example.grindandgrow.screens.HabitScreen
import com.example.grindandgrow.screens.HomeScreen
import com.example.grindandgrow.screens.ReportScreen
import com.example.grindandgrow.screens.firstscreen
import com.example.grindandgrow.screens.login
import com.example.grindandgrow.screens.register
import com.example.grindandgrow.screens.splashscreen

@Composable
fun AppNavHost(
    modifier: Modifier= Modifier,
    navController: NavHostController= rememberNavController(),
    startDestination: String = ROUTE_SPLASH
    ) {
    val navController=rememberNavController()
    val vm: MainViewModel= viewModel()
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(ROUTE_SPLASH) {
            splashscreen(navController)
        }
        composable(ROUTE_FIRSTSCREEN) {
            firstscreen(navController)
        }
        composable(ROUTE_LOGIN) {
            login(navController)
        }
        composable(ROUTE_REGISTER) {
            register(navController)
        }
        composable(ROUTE_HABIT) {
            HabitScreen(navController,vm)
        }
        composable(ROUTE_BUDGET) {
            BudgetScreen(navController,vm)
        }
        composable(ROUTE_HOME) {
            HomeScreen(navController,vm)
        }
        composable(ROUTE_REPORT) {
            ReportScreen(navController,vm)
        }

    }
}

