package com.example.petcare

import android.content.Context
import androidx.compose.runtime.snapshots.SnapshotStateList
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.time.LocalDate


const val TASK_FILE = "task.dat"
const val TASK_BACKUP = "task-backup.dat"
const val TIME_CHANGE = "time-track.dat"
const val APPOINTMENT_FILE = "appt-names.dat"
const val APPOINTMENT_DATE = "appt-dates.dat"


// Streamlining input and output

fun writeDate(currentdate: LocalDate,context: Context,  fileName: String){
    val fos1 = context.openFileOutput(fileName, Context.MODE_PRIVATE)
    val oas1 = ObjectOutputStream(fos1)

    oas1.writeObject(currentdate)
    oas1.close()
}

fun readDate(context:Context, fileName: String): LocalDate{

    var prevDate: LocalDate
    try {
        val fis = context.openFileInput(fileName)
        val ois = ObjectInputStream(fis)

        prevDate = ois.readObject() as LocalDate

    } catch (e : FileNotFoundException) {
        prevDate = LocalDate.of(2023, 10, 20)
    }

    return prevDate
}

fun writeData(data: SnapshotStateList<String>, context: Context, fileName: String) {
    val oas: ObjectOutputStream

    val dataList = ArrayList<String>()
    dataList.addAll(data)

    val fos: FileOutputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE)
    oas = ObjectOutputStream(fos)
    oas.writeObject(dataList)
    oas.close()
}

fun readData(context: Context, fileName: String): SnapshotStateList<String> {
    var dataList: ArrayList<String>

    try {
        val ois: ObjectInputStream

        val fis: FileInputStream = context.openFileInput(fileName)
        ois = ObjectInputStream(fis)
        dataList = ois.readObject() as ArrayList<String>
    } catch (e: FileNotFoundException) {
        dataList = ArrayList()
    }

    val data = SnapshotStateList<String>()
    data.addAll(dataList)

    return data
}


