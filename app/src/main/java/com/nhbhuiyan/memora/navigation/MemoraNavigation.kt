package com.nhbhuiyan.memora.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nhbhuiyan.memora.presentation.ui.Screen.InsertNote
import com.nhbhuiyan.memora.presentation.ui.Screen.NotesListScreen
import com.nhbhuiyan.memora.presentation.ui.Screen.SplashScreen

@Composable
fun MemoraNavigation(navHostController: NavHostController){
    NavHost(navController = navHostController, startDestination = "SplashScreen"){
        composable(route = "SplashScreen") { SplashScreen(navHostController) }
        composable(route = "NotesListScreen") { NotesListScreen(navHostController) }
        composable(route="InsertNote") { InsertNote(navHostController) }
    }
}