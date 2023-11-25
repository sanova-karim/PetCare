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
fun AddPetAppointment(
    appointmentName: MutableState<String>,
    appointmentDate: MutableState<String>,
    focusManager: FocusManager,
    appointmentList: SnapshotStateList<String>,
    appointmentDateList: SnapshotStateList<String>,
    myContext: Context,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = appointmentName.value,
            onValueChange = {
                appointmentName.value = it
            },
            label = { Text(text = "Pet appointment") },
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
                .weight(5F) //3F for the button weight
                .height(50.dp),
            textStyle = TextStyle(textAlign = TextAlign.Center)
        )

        Spacer(modifier = Modifier.width(5.dp))

        TextField(
            value = appointmentDate.value,
            onValueChange = {
                appointmentDate.value = it          //mnk
            },
            label = { Text(text = "Date") },
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
                .weight(2F) //3F for the button weight
                .height(50.dp),
            textStyle = TextStyle(textAlign = TextAlign.Center)
        )

        Spacer(modifier = Modifier.width(5.dp))

        Button(
            onClick = {
                if ((appointmentName.value.isNotEmpty()) && (appointmentDate.value.isNotEmpty())) {
                    appointmentList.add(appointmentName.value)
                    writeData(appointmentList, myContext, APPOINTMENT_FILE)
                    appointmentName.value = ""

                    appointmentDateList.add(appointmentDate.value)
                    writeData(appointmentDateList, myContext, APPOINTMENT_DATE)
                    appointmentDate.value = ""
                    focusManager.clearFocus()
                }
                if ((appointmentName.value.isEmpty()) && (appointmentName.value.isEmpty()))
                {
                    Toast.makeText(myContext, "Enter appointment name and date", Toast.LENGTH_SHORT).show()
                }
                if (appointmentName.value.isEmpty())
                {
                    Toast.makeText(myContext, "Enter appointment name", Toast.LENGTH_SHORT).show()
                }
                if (appointmentDate.value.isEmpty())
                {
                    Toast.makeText(myContext, "Enter appointment date", Toast.LENGTH_SHORT).show()
                }

            },
            modifier = Modifier
                .weight(1F)
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