package com.example.petcare

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable

//This function has "Pet task" field and "+" field
//An user by typing in the tab for Pet task adds the name of the task
//and by pressing + the task is added to a list and saved in files
fun AddPetTask(
    taskName: MutableState<String>,
    focusManager: FocusManager,
    itemList: SnapshotStateList<String>,
    myContext: Context,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = taskName.value,
            onValueChange = {
                taskName.value = it
            },
            label = { Text(text = "Pet task") },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedLabelColor = Color.Green,
                unfocusedLabelColor = Color.White,
                containerColor = colorResource(id = R.color.custom7),
                textColor = Color.White,
                cursorColor = Color.White
            ),
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier
                .border(1.dp, Color.Black, RoundedCornerShape(5.dp))
                .weight(7F) //3F for the button weight
                .height(50.dp),

            textStyle = TextStyle(textAlign = TextAlign.Center)

        )

        Spacer(modifier = Modifier.width(5.dp))

        Button(
            onClick = {
                if (taskName.value.isNotEmpty()) {
                    itemList.add(taskName.value)
                    writeData(itemList, myContext, TASK_FILE) //this file saves today's task
                    writeData(itemList, myContext, TASK_BACKUP) //this file saves all recurring tasks
                    taskName.value = ""
                    focusManager.clearFocus()
                } else {
                    Toast.makeText(myContext, "Enter a task", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier
                .weight(3F)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.custom7),
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(5.dp),
            border = BorderStroke(1.dp, Color.Black)
        ) {
            Text(text = "+", fontSize = 20.sp)
        }
    }
}