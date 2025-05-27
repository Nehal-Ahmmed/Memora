package com.nhbhuiyan.memora.presentation.ui.Screen.NotesListScreen

import androidx.compose.foundation.background
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.nhbhuiyan.memora.data.local.LocalNote
import com.nhbhuiyan.memora.navigation.navNew.BottomNavigationBar
import com.nhbhuiyan.memora.presentation.ui.theme.lightBlack
import com.nhbhuiyan.memora.presentation.viewmodel.NoteViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun NotesListScreen(navHostController: NavHostController, viewModel: NoteViewModel = viewModel()) {
    //scroll
    val scrollState = rememberScrollState()
    //fetching noteslist from room database
    val notesList = viewModel.allNotes.collectAsState(initial = emptyList())
    //search result
    var search by remember { mutableStateOf("") }
    val searchedNotes by viewModel.notes.collectAsState(initial = emptyList())
    // Update search query with debounce
    LaunchedEffect(search) { viewModel.onSearchQueryChanged(search)}

    //date time
    val dateTime=LocalDateTime.now()
    val formattedDateTime=dateTime.format(
        DateTimeFormatter.ofPattern("MMM dd, yyy - hh:mm a")
    )


    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navHostController.navigate("InsertNote")
                },
                modifier = Modifier
                    .padding(vertical = 16.dp, horizontal = 16.dp)
                    .size(64.dp),
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
        },
        bottomBar = { BottomNavigationBar(navHostController) }
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(color = Color.Black)
        ) {
            val (titleBar, topicTxt, searchBar, mainColumn,lastSpace) = createRefs()
            //top bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(color = Color.Transparent)
                    .constrainAs(titleBar) { top.linkTo(parent.top) },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.padding(end = 12.dp)
                )
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.padding(end = 16.dp)
                )
            }
            //topic name 'Notes'
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(topicTxt) {
                        top.linkTo(titleBar.bottom)
                    }
            ) {
                Text(
                    text = "Notes",
                    fontSize = 35.sp,
                    fontWeight = FontWeight.Thin,
                    color = Color.White,
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .weight(1f)
                )
            }

            // Search bar
            TextField(
                value = search,
                onValueChange = { search = it },
                placeholder = {
                    Text(
                        text = "Search notes",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(24.dp)
                    )
                },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    cursorColor = MaterialTheme.colorScheme.primary,
                    focusedTextColor = MaterialTheme.colorScheme.onSurface,
                    unfocusedTextColor = MaterialTheme.colorScheme.onSurface
                ),
                shape = CircleShape,
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search,
                    capitalization = KeyboardCapitalization.Sentences
                ),
                modifier = Modifier
                    .constrainAs(searchBar) {
                        top.linkTo(topicTxt.bottom)
                    }
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(vertical = 8.dp)
            )

            //list item
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp)
                    .verticalScroll(scrollState)
                    .constrainAs(mainColumn) {
                        top.linkTo(searchBar.bottom)
                    }
            ) {
                Spacer(Modifier.height(12.dp))
                searchedNotes.forEach { note ->
                    listItem(navHostController, id = note.id, note.title, note.description,formattedDateTime)
                    Spacer(Modifier.height(10.dp))
                }
                Spacer(Modifier.padding(160.dp))
            }
        }
    }
}
