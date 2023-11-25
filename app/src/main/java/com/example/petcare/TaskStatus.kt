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
//This function handle operation depending on whether user want
//to change the task, delete the task or indicates that the task is done
fun HandleTask( myContext: Context,
                itemList: SnapshotStateList<String>,
                taskNameStatus: MutableState<Boolean>,
                checkStatus: MutableState<Boolean>,
                deleteStatus: MutableState<Boolean>,
                clickedItem: MutableState<String>,
                clickedItemIndex: MutableState<Int>,

            ) {

    //If user want to modify the Task, this call takes care of that
    ChangeTask(
        taskNameStatus,
        clickedItem,
        itemList,
        clickedItemIndex,
        myContext
    )

    //if user checked delete button, this calls take care of that
    DeleteTask(
        deleteStatus,
        itemList,
        clickedItemIndex,
        myContext
    )

    //if user checked Check button, handle that properly
    CheckTask(
        checkStatus,
        itemList,
        clickedItemIndex,
        myContext
    )


}


//Function to handle if user clicked text portion of the task
//This will enable user to change the name of the task
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangeTask(
    taskNameStatus: MutableState<Boolean>,
    clickedItem: MutableState<String>,
    itemList: SnapshotStateList<String>,
    clickedItemIndex: MutableState<Int>,
    myContext: Context
) {
    if (taskNameStatus.value) {
        AlertDialog(
            onDismissRequest = { taskNameStatus.value = false },
            title = {
                Text(text = "Enter new task name")
            },
            text = {
                TextField(
                    value = clickedItem.value,
                    onValueChange = {
                        clickedItem.value = it
                    }
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        itemList[clickedItemIndex.value] = clickedItem.value
                        writeData(itemList, myContext, TASK_FILE)
                        writeData(itemList, myContext, TASK_BACKUP)
                        taskNameStatus.value = false
                        Toast.makeText(myContext, "Item is updated", Toast.LENGTH_SHORT).show()
                    }
                ) {
                    Text(text = "YES")
                }
            },
            dismissButton = {
                TextButton(onClick = { taskNameStatus.value = false }) {
                    Text(text = "NO")
                }
            }
        )
    }
}

//Function to handle if user checked delete button
@Composable
fun DeleteTask(
    deleteStatus: MutableState<Boolean>,
    itemList: SnapshotStateList<String>,
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
                        itemList.removeAt(clickedItemIndex.value)
                        writeData(itemList, myContext, TASK_FILE)
                        writeData(itemList, myContext, TASK_BACKUP)
                        deleteStatus.value = false
                        Toast.makeText(myContext, "Item is removed from the list", Toast.LENGTH_SHORT).show()
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


//Function to handle if user checked Check button indicating the task is done for today
@Composable
fun CheckTask(
    checkStatus: MutableState<Boolean>,
    itemList: SnapshotStateList<String>,
    clickedItemIndex: MutableState<Int>,
    myContext: Context
) {
    if (checkStatus.value) {
        AlertDialog(
            onDismissRequest = { checkStatus.value = false },
            title = {
                Text(text = "Done")
            },
            text = {
                Text(text = "Are you done with this task?")
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        itemList.removeAt(clickedItemIndex.value)
                        writeData(itemList, myContext, TASK_FILE)
                        checkStatus.value = false
                        Toast.makeText(myContext, "Item is removed from today's list", Toast.LENGTH_SHORT).show()
                    }
                ) {
                    Text(text = "YES")
                }
            },
            dismissButton = {
                TextButton(onClick = { checkStatus.value = false }) {
                    Text(text = "NO")
                }
            }
        )
    }
}



