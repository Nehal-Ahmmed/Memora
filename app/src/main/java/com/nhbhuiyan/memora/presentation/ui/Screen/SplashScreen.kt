package com.nhbhuiyan.memora.presentation.ui.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.nhbhuiyan.memora.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navHostController: NavHostController){
    LaunchedEffect(Unit) {
        delay(2000)
        navHostController.navigate("NotesListScreen"){
            popUpTo("SplashScreen"){
                inclusive=true
            }
        }
    }

    Scaffold {
        ConstraintLayout (modifier = Modifier.fillMaxSize().padding(it).background(color = colorResource(R.color.grey))){
            val(iconbox)=createRefs()
            Box(
                modifier = Modifier.height(255.dp)
                    .width(255.dp)
                    .background(color = colorResource(R.color.blue), shape = CircleShape)
                    .constrainAs(iconbox){
                        centerTo(parent)
                    }
            ){
                    Image(
                        painter = painterResource(R.drawable.noteimage),
                        contentDescription = null,
                        modifier = Modifier.align(Alignment.Center).size(125.dp)
                    )
            }
        }
    }
}