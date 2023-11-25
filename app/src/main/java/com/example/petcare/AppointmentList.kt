package com.example.petcare

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun AppointmentList(
    appointmentList: SnapshotStateList<String>,
    appointmentDateList: SnapshotStateList<String>,
    checkStatus: MutableState<Boolean>,
    deleteStatus: MutableState<Boolean>,
    dateStatus: MutableState<Boolean>,
    appointmentNameStatus: MutableState<Boolean>,
    clickedItemIndex: MutableState<Int>,
    clickedItem: MutableState<String>,
) {
    LazyColumn {
        items(
            count = appointmentList.size,
            itemContent = { index ->
                val item = appointmentList[index]
                val dueDate = appointmentDateList[index]    //mnk

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 1.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = colorResource(id = R.color.custom2),
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(0.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = item,
                            color = Color.White,
                            fontSize = 16.sp,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier
                                .width(125.dp)
                                .clickable {
                                    clickedItem.value = item
                                    clickedItemIndex.value = index
                                    appointmentNameStatus.value = true
                                }
                        )

                        Text( //mnk
                            text = dueDate,
                            color = Color.White,
                            fontSize = 16.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier
                                .width(75.dp)
                                .clickable {
                                    clickedItem.value = dueDate
                                    clickedItemIndex.value = index
                                    dateStatus.value = true
                                }
                        )

                        Row {

                            IconButton(onClick = {
                                deleteStatus.value = true
                                clickedItemIndex.value = index
                                clickedItem.value = item
                            }) {
                                Icon(
                                    Icons.Filled.Delete,
                                    contentDescription = "delete",
                                    tint = colorResource(id = R.color.custom4)
                                )
                            }

                            //Spacer(modifier = Modifier.width(75.dp))

                            IconButton(onClick = {
                                checkStatus.value = true
                                clickedItemIndex.value = index
                                clickedItem.value = item
                            }) {
                                Icon(
                                    Icons.Filled.Check,
                                    contentDescription = "checked",
                                    tint = colorResource(id = R.color.custom5)
                                )
                            }

                        }
                    }
                }
            }
        )
    }
}