package com.example.petcare

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

// The second page provides user an way to enter any text like a scratch pad
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondPage(navController: NavController, scratchPadViewModel: ScratchPadViewModel, myContext: Context) {
    var isEditing = remember { mutableStateOf(false) }
    val scratchPadStorage = remember { ScratchPadStorage(myContext) }

    // Load the scratch pad text from storage when the composable is first created
    val initialText = scratchPadStorage.getScratchPadText()
    if (initialText.isNotEmpty()) {
        scratchPadViewModel.scratchPadText.value = initialText
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Button(
            onClick = {
                // Open google search engine
                val url = "https://google.com"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                myContext.startActivity(intent)
            },
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text("Google Search")
        }
        // Scratch pad for notes
        TextField(
            value = scratchPadViewModel.scratchPadText.value,
            onValueChange = { newNotes ->
                scratchPadViewModel.scratchPadText.value = newNotes
                scratchPadStorage.setScratchPadText(newNotes)
            },

            label = {
                // Use a custom TextStyle for the label
                Text(
                    text = "Scratch pad",
                    style = TextStyle(
                        fontSize = 20.sp, // Adjust the font size as needed
                        fontWeight = FontWeight.Bold, // Make it bold if desired
                        textAlign = TextAlign.Left,
                        color = colorResource(id = R.color.custom3)
                    ),
                )
            },

            textStyle = TextStyle(textAlign = TextAlign.Left),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedLabelColor = Color.Green,
                unfocusedLabelColor = Color.White,
                containerColor = colorResource(id = R.color.custom7),
                textColor = Color.White,
                cursorColor = Color.White
            ),
            singleLine = false,
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .background(MaterialTheme.colorScheme.background)
                .border(1.dp, Color.Gray, RoundedCornerShape(5.dp))
                .padding(16.dp)
                .clickable {
                    // Set isEditing to true when the user clicks on the TextField
                    isEditing.value = true
                }
        )

        // Display a transparent overlay when editing to prevent interaction with other elements
        if (isEditing.value) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent)
                    .clickable {
                        // Set isEditing to false when the overlay is clicked
                        isEditing.value = false
                    }
            )
        }


        Spacer(modifier = Modifier.height(50.dp))

        //This navigation button is to navigate back to the first page with tasks and appointments
        Button(
            onClick = {
                navController.navigate("first_page")
            },
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally) // Center the button horizontally
                .alpha(if (isEditing.value) 0f else 1f)
        ) {
            Text("Tasks and appointments")
        }
    }
}


