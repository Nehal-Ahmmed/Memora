package com.nhbhuiyan.memora.presentation.ui.Screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.nhbhuiyan.memora.navigation.navOld.MemoraNavigation
import com.nhbhuiyan.memora.presentation.ui.theme.MemoraTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MemoraTheme {
                mainScreen()
            }
        }
    }
}


@Composable
fun mainScreen(){
    val navHostController= rememberNavController()
    MemoraNavigation(navHostController)
}