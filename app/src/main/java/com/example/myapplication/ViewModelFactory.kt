package com.example.myapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.myapplication.screens.register.RegisterViewModel

val CoPassViewModelFactory = object : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T =
        with(modelClass) {
            when {
                isAssignableFrom(RegisterViewModel::class.java) ->
                    RegisterViewModel()
                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }

        } as T
}