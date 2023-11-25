package com.example.petcare

import android.content.Context
import android.widget.Toast
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.material3.TextField


@Composable
fun HandleAppointment(
    appointmentNameStatus: MutableState<Boolean>,
    dateStatus: MutableState<Boolean>,
    checkStatus: MutableState<Boolean>,
    deleteStatus: MutableState<Boolean>,
    appointmentList: SnapshotStateList<String>,
    appointmentDateList: SnapshotStateList<String>,
    clickedAppointment: MutableState<String>,
    clickedAppointmentIndex: MutableState<Int>,
    myContext: Context
) {

    //if user clicked text part of appointment, handle that properly
    ChangeAppointmentName(
        appointmentNameStatus,
        clickedAppointmentIndex,
        clickedAppointment,
        appointmentList,
        myContext)

    //If user clicked date part of appointment to change the data, handle that properly
    ChangeAppointmentDate(
        dateStatus,
        clickedAppointmentIndex,
        clickedAppointment,
        appointmentDateList,
        myContext
    )

    //if user checked delete button, handle that properly
    DeleteAppointment(  //mnk
        deleteStatus,
        appointmentList,
        appointmentDateList,
        clickedAppointmentIndex,
        myContext
    )

    //if user checked Check button, handle that properly
    CheckAppointment(
        checkStatus,
        appointmentList,
        appointmentDateList,
        clickedAppointmentIndex,
        myContext
    )

}

//  Function to handle if user checked text part the appointment name

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangeAppointmentName(
    appointmentNameStatus: MutableState<Boolean>,
    clickedAppointmentIndex: MutableState<Int>,
    clickedAppointment: MutableState<String>,
    appointmentList: SnapshotStateList<String>,
    myContext: Context
) {
    if (appointmentNameStatus.value) {
        AlertDialog(
            onDismissRequest = { appointmentNameStatus.value = false },
            title = {
                Text(text = "Enter new appt. name")
            },
            text = {
                TextField(
                    value = clickedAppointment.value,
                    onValueChange = {
                        clickedAppointment.value = it
                    }
                )

            },
            confirmButton = {
                TextButton(
                    onClick = {
                        appointmentList[clickedAppointmentIndex.value] =
                            clickedAppointment.value

                        writeData(appointmentList, myContext, APPOINTMENT_FILE)
                        appointmentNameStatus.value = false
                        Toast.makeText(myContext, "Appointment is updated", Toast.LENGTH_SHORT)
                            .show()
                    }
                ) {
                    Text(text = "YES")
                }
            },
            dismissButton = {
                TextButton(onClick = { appointmentNameStatus.value = false }) {
                    Text(text = "NO")
                }
            }
        )
    }
}

//Function to handle if user checked text part the appointment date
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangeAppointmentDate(
    dateStatus: MutableState<Boolean>,
    clickedAppointmentIndex: MutableState<Int>,
    clickedAppointment: MutableState<String>,
    appointmentDateList: SnapshotStateList<String>,
    myContext: Context
) {
    if (dateStatus.value) {

        AlertDialog(
            onDismissRequest = { dateStatus.value = false },
            title = {
                Text(text = "Enter new date")
            },
            text = {
                TextField(
                    value = clickedAppointment.value,
                    onValueChange = {
                        clickedAppointment.value = it
                    }
                )

            },
            confirmButton = {
                TextButton(
                    onClick = {
                        appointmentDateList[clickedAppointmentIndex.value] =
                            clickedAppointment.value
                        writeData(appointmentDateList, myContext, APPOINTMENT_DATE)
                        dateStatus.value = false
                        Toast.makeText(myContext, "Appointment date is updated", Toast.LENGTH_SHORT)
                            .show()
                    }
                ) {
                    Text(text = "YES")
                }
            },
            dismissButton = {
                TextButton(onClick = { dateStatus.value = false }) {
                    Text(text = "NO")
                }
            }
        )
    }
}


//Function to handle if user checked delete button
@Composable
fun DeleteAppointment(
    deleteStatus: MutableState<Boolean>,
    itemList1: SnapshotStateList<String>,
    itemList2: SnapshotStateList<String>,
    clickedItemIndex: MutableState<Int>,
    myContext: Context
) {
    if (deleteStatus.value) {
        AlertDialog(
            onDismissRequest = { deleteStatus.value = false },
            title = {
                Text(text = "Delete")
            },
            text = {
                Text(text = "Do you want to delete this item from the list")
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        itemList1.removeAt(clickedItemIndex.value)
                        writeData(itemList1, myContext, APPOINTMENT_FILE)

                        itemList2.removeAt(clickedItemIndex.value)
                        writeData(itemList2, myContext, APPOINTMENT_DATE)

                        deleteStatus.value = false
                        Toast.makeText(myContext, "Appointment is deleted", Toast.LENGTH_SHORT)
                            .show()
                    }
                ) {
                    Text(text = "YES")
                }
            },
            dismissButton = {
                TextButton(onClick = { deleteStatus.value = false }) {
                    Text(text = "NO")
                }
            }
        )
    }
}


//Function to handle if user checked Check button
@Composable
fun CheckAppointment(
    checkDialogStatus: MutableState<Boolean>,
    itemList: SnapshotStateList<String>,
    itemList2: SnapshotStateList<String>,
    clickedItemIndex: MutableState<Int>,
    myContext: Context
) {
    if (checkDialogStatus.value) {
        AlertDialog(
            onDismissRequest = { checkDialogStatus.value = false },
            title = {
                Text(text = "Done")
            },
            text = {
                Text(text = "Are you done with?")
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        itemList.removeAt(clickedItemIndex.value)
                        writeData(itemList, myContext, APPOINTMENT_FILE)

                        itemList2.removeAt(clickedItemIndex.value)
                        writeData(itemList2, myContext, APPOINTMENT_DATE)

                        checkDialogStatus.value = false
                        Toast.makeText(
                            myContext,
                            "Item is removed from today's list",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                ) {
                    Text(text = "YES")
                }
            },
            dismissButton = {
                TextButton(onClick = { checkDialogStatus.value = false }) {
                    Text(text = "NO")
                }
            }
        )
    }
}
