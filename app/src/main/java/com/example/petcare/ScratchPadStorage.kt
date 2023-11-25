package com.example.petcare

import android.content.Context
import android.content.SharedPreferences

//Declares the class to provide a mechanism to save
//scratch pad data across application run
class ScratchPadStorage(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("scratch_pad", Context.MODE_PRIVATE)

    fun getScratchPadText(): String {
        return sharedPreferences.getString("scratch_pad_text", "") ?: ""
    }

    fun setScratchPadText(text: String) {
        sharedPreferences.edit().putString("scratch_pad_text", text).apply()
    }
}