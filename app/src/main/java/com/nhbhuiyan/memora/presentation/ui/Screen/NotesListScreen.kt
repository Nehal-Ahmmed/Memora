package com.nhbhuiyan.memora.presentation.ui.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.nhbhuiyan.memora.R
import com.nhbhuiyan.memora.domain.model.Note
import com.nhbhuiyan.memora.presentation.ui.theme.black
import com.nhbhuiyan.memora.presentation.viewmodel.NoteViewModel

@Composable
fun NotesListScreen(navHostController: NavHostController,viewModel: NoteViewModel= viewModel()) {
    //fetching noteslist from room database
    val notesList=viewModel.allNotes.collectAsState(initial = emptyList())
    //scroll
    val scrollState = rememberScrollState()


    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navHostController.navigate("InsertNote")
                },
                modifier = Modifier.padding(vertical = 16.dp, horizontal = 16.dp).size(64.dp),
                containerColor = Color.Red,
                contentColor = Color.White,
                shape = CircleShape,
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    modifier = Modifier.size(36.dp)
                )
            }
        }
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(color = Color.Black)
        ) {
            val (titleBar, mainColumn) = createRefs()
            //top bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(color = Color.Gray)
                    .constrainAs(titleBar) { top.linkTo(parent.top) },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Your Notes",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .weight(1f)
                )
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.padding(end = 12.dp)
                )
            }

            //list item
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp)
                    .verticalScroll(scrollState)
                    .constrainAs(mainColumn) {
                        top.linkTo(titleBar.bottom)
                    }
            ) {
                Spacer(Modifier.height(12.dp))
                notesList.value.forEach { note ->
                    listItem(note.title, note.description)
                    Spacer(Modifier.height(8.dp))
                }
            }
        }
    }
}

//function for item design
@Composable
fun listItem(title: String, description: String) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp)
            .background(color = black, shape = RoundedCornerShape(10.dp))
    ) {
        Column(modifier = Modifier.padding(horizontal = 8.dp)) {
            Spacer(Modifier.height(12.dp))
            Row {
                Text(
                    text = title,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp,
                    color = Color.White,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp)
                )
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .padding(end = 12.dp)
                        .clickable {
                            expanded = true
                        }
                )
            }
            Spacer(Modifier.height(18.dp))
            Text(
                text = description,
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp,
                color = Color.White,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(bottom = 18.dp)
            )
            Spacer(Modifier.height(8.dp))
        }
        //dropdown menu
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            properties = PopupProperties(clippingEnabled = true),
            offset = DpOffset(x = 300.dp, y = -80.dp)
        ) {
            DropdownMenuItem(
                text = { Text("Update") },
                onClick = {}
            )
            DropdownMenuItem(
                text = { Text("Delete") },
                onClick = {}
            )
        }
    }
}