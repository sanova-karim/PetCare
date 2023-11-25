package com.example.petcare

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.petcare.ui.theme.Petsy3Theme

//Scratch pad view model provides the mechanism to maintain
//scratch pad contents switching between first and second pages
//via scratch pad text
//First page is for various tasks and appointments and second page
//is scratch pad for random pet info

class ScratchPadViewModel : ViewModel() {
    // Shared mutable state for the scratch pad text
    var scratchPadText =  mutableStateOf("")
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Petsy3Theme {
                // A surface container using the 'background' color from the theme

                val scratchPadViewModel: ScratchPadViewModel = viewModel()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainContent(scratchPadViewModel)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent(scratchPadViewModel: ScratchPadViewModel) {
    val navController = rememberNavController()
    val myContext = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = "first_page" //Ensures that app opens on task/appointment page
    ) {
        composable("first_page")
        {
            FirstPage(navController)
        }
        composable("second_page") //This page can gives scratch pad to put any pet info
        {
            //SecondPage(navController)
            SecondPage(navController, scratchPadViewModel, myContext) //mnk
        }
    }
}


