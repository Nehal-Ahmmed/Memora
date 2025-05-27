package com.nhbhuiyan.memora.presentation.ui.Screen.NotesListScreen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.nhbhuiyan.memora.presentation.viewmodel.NoteViewModel

@Composable
fun InsertNote(
    navHostController: NavHostController,
    fetchedId: String? = null,
    fetchedTitle: String = "",
    fetchedDescription: String = "",
    viewModel: NoteViewModel = viewModel()
) {
    val context = LocalContext.current
    var title by remember { mutableStateOf(fetchedTitle) }
    var description by remember { mutableStateOf(fetchedDescription) }

    Scaffold(
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(color = Color.Black)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
                    .background(color = Color.Black)
            ) {
                Spacer(Modifier.height(18.dp))
                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = "Add Note",
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp).weight(1f),
                        color = Color.White,
                        fontSize = 35.sp,
                        fontWeight = FontWeight.Thin
                    )
                    Icon(
                        imageVector = Icons.Default.Done,
                        contentDescription = null,
                        modifier = Modifier
                            .size(32.dp)
                            .clickable {
                                if(title.isBlank()){
                                    Toast.makeText(context,"Title cannot be empty",Toast.LENGTH_SHORT).show()
                                    return@clickable
                                }
                                viewModel.addNote(fetchedId ?: "",title, description)
                                Toast.makeText(context, "'${title}' is added", Toast.LENGTH_LONG).show()
                                navHostController.popBackStack()
                                navHostController.navigate("NotesListScreen")
                            }
                    )
                    Spacer(Modifier.width(8.dp))
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(color = MaterialTheme.colorScheme.surface)
                ) {

                    TextField(
                        value = title,
                        onValueChange = { title = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp),
                        placeholder = {
                            Text(
                                text = "Enter your title here",
                                style = typography.bodyMedium,
                                color = Color.White,
                                fontSize = 16.sp
                            )
                        },

                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent,
                            focusedTextColor = Color.Transparent,
                            unfocusedTextColor = Color.Transparent,
                        ),
                        shape = RoundedCornerShape(16.dp),
                        singleLine = true,
                        maxLines = 1,
                        textStyle = TextStyle(
                            fontSize = 26.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Light
                        )
                    )
                }
                Spacer(Modifier.height(16.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(390.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(color = MaterialTheme.colorScheme.background)
                ) {
                    TextField(
                        value = description,
                        onValueChange = { description = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(horizontal = 12.dp),
                        placeholder = {
                            Text(
                                text = "Enter your note here",
                                style = typography.bodyMedium,
                                fontSize = 16.sp,
                                color = Color.White
                            )
                        },

                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent,
                            focusedTextColor = Color.Transparent,
                            unfocusedTextColor = Color.Transparent,

                            ),
                        shape = RoundedCornerShape(10.dp),
                        textStyle = TextStyle(
                            fontSize = 22.sp,
                            color = Color.White
                        )
                    )
                }
            }
        }
    }
}