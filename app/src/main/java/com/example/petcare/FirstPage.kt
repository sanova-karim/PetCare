package com.example.petcare

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FirstPage(navController: NavController) {

    val myContext = LocalContext.current

    val focusManager = LocalFocusManager.current

    val taskName = remember {
        mutableStateOf("")
    }
    val appointmentName = remember {
        mutableStateOf("")
    }
    val appointmentDate = remember{
        mutableStateOf("")
    }
    val taskNameStatus = remember {
        mutableStateOf(false)
    }

    val deleteTaskStatus= remember {
        mutableStateOf(false)
    }
    val checkTaskStatus = remember {
        mutableStateOf(false)
    }

    val appointmentNameStatus = remember {
        mutableStateOf(false)
    }
    val dateStatus = remember {
        mutableStateOf(false)
    }
    val deleteAppointmentStatus= remember {
        mutableStateOf(false)
    }
    val checkAppointmentStatus = remember {
        mutableStateOf(false)
    }


    val clickedItemIndex = remember {
        mutableIntStateOf(0)
    }
    val clickedItem = remember {
        mutableStateOf("")
    }
    val clickedAppointmentIndex = remember {
        mutableIntStateOf(0)
    }
    val clickedAppointment = remember {
        mutableStateOf("")
    }

    val dateChanged = remember {
        mutableStateOf(false)
    }

    val currentDate = LocalDate.now()
    val previousDate = readDate(myContext, TIME_CHANGE)

    if ((currentDate.year != previousDate.year) || (currentDate.month != previousDate.month) || (currentDate.dayOfMonth != previousDate.dayOfMonth)) {
        dateChanged.value = true
    }

    val itemList: SnapshotStateList<String> = if (dateChanged.value) {
        readData(myContext, TASK_BACKUP)

    } else {
        readData(myContext, TASK_FILE)
    }

    val appointmentList = readData(myContext, "pet-apt.dat")
    val appointmentDateList = readData(myContext, "pet-date.dat")


    writeData(itemList, myContext, TASK_FILE)
    writeDate(currentDate, myContext,TIME_CHANGE)


    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {


        //Top half column of the first page
        Column(
            //modifier = Modifier.fillMaxSize()
            modifier = Modifier
                .fillMaxWidth()
                .weight(4f)
        ) {

            //This function creates UI for user to add a recurring Pet Task
            AddPetTask(
                taskName,
                focusManager,
                itemList,
                myContext,
            )

            //Now create the task list based on user input in AddPetTask
            CreateTaskList(
                itemList,
                taskNameStatus,
                deleteTaskStatus,
                checkTaskStatus,
                clickedItemIndex,
                clickedItem,
            )

            //Handle if user pressed any of the buttons - text dialog, update, check and delete
            HandleTask(
                myContext,
                itemList,
                taskNameStatus,
                checkTaskStatus,
                deleteTaskStatus,
                clickedItem,
                clickedItemIndex,

            )
        }
        //Bottom half of the column for the first page

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(3f)
        ) {

            //UI for user to create appointments
            AddPetAppointment(
                appointmentName,
                appointmentDate,
                focusManager,
                appointmentList,
                appointmentDateList,
                myContext,
            )

            //Based on user input create appointment list
            AppointmentList(
                appointmentList,
                appointmentDateList,
                checkAppointmentStatus,
                deleteAppointmentStatus,
                dateStatus,
                appointmentNameStatus,
                clickedAppointmentIndex,
                clickedAppointment
            )

            //Handle if user pressed any of the buttons - text dialog, date dialog, update and check
            HandleAppointment(
                appointmentNameStatus,
                dateStatus,
                checkAppointmentStatus,
                deleteAppointmentStatus,
                appointmentList,
                appointmentDateList,
                clickedAppointment,
                clickedAppointmentIndex,
                myContext
            )

        }

        //One additional column to make sure that we can put
        //a tab fo go the second page that has pet info in
        //scratch pad
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.Center, // Vertically center-align children
            horizontalAlignment = Alignment.CenterHorizontally // Horizontally center-align childre

        ) {
            Button(
                onClick = {
                    navController.navigate("second_page")
                },
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Pet Info")
            }
        }
    }
}
