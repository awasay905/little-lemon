package com.example.littlelemon

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun MyNavigation(navController: NavController, databas: AppDatabas) {
    val isDataStored = LocalContext.current.getSharedPreferences("LittleLemonApp", Context.MODE_PRIVATE).getBoolean("onboarding", true)
    NavHost(navController = navController as NavHostController, startDestination = if (isDataStored) OnBoarding.route else Home.route){
        composable(OnBoarding.route){
            Onboarding(navController)
        }
        composable(Home.route){
            Home(navController, databas)
        }
        composable(Profile.route){
             Profile(navController)
        }
    }

}