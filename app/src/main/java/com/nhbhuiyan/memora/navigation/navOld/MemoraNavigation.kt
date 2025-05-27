package com.nhbhuiyan.memora.navigation.navOld

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.nhbhuiyan.memora.presentation.ui.Screen.ArchiveScreen.archiveScreen
import com.nhbhuiyan.memora.presentation.ui.Screen.FavoriteScreen.favoriteScreen
import com.nhbhuiyan.memora.presentation.ui.Screen.NotesListScreen.InsertNote
import com.nhbhuiyan.memora.presentation.ui.Screen.NotesListScreen.NotesListScreen
import com.nhbhuiyan.memora.presentation.ui.Screen.SettingScreen.settingsScreen
import com.nhbhuiyan.memora.presentation.ui.Screen.splashScreen.SplashScreen

@Composable
fun MemoraNavigation(navHostController: NavHostController){
    NavHost(navController = navHostController, startDestination = "SplashScreen"){
        composable(route = "SplashScreen") { SplashScreen(navHostController) }
        composable(route = "NotesListScreen") { NotesListScreen(navHostController) }
        composable(route="InsertNote") { InsertNote(navHostController) } //normal call
        //pass activity as well as data by this route
        composable(
            route="InsertNote/{id}/{title}/{description}",
            arguments = listOf(
    navArgument("id"){type= NavType.StringType},
    navArgument("title"){type= NavType.StringType},
    navArgument("description"){type= NavType.StringType}
            )
        ) {backStackEntry->
            InsertNote(
                navHostController,
                fetchedId=backStackEntry.arguments?.getString("id")?: "",
                fetchedTitle = backStackEntry.arguments?.getString("title")?: "",
                fetchedDescription = backStackEntry.arguments?.getString("description")?: ""
            )
        }

        //other screen of bottom navigation bar
        composable("FavoritesScreen") { favoriteScreen(navHostController) }
        composable("ArchiveScreen") { archiveScreen(navHostController) }
        composable("SettingsScreen") { settingsScreen(navHostController) }
    }
}