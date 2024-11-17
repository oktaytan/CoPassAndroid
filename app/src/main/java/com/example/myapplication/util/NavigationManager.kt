package com.example.myapplication.util

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.example.myapplication.R
import java.util.function.BinaryOperator

object NavigationManager {

    @SuppressLint("StaticFieldLeak")
    private var navController: NavController? = null

    fun setNavController(controller: NavController) {
        navController = controller
    }

    fun navigate(destination: Destination, args: Bundle? = null, navOptions: NavOptions? = null) {
        navController?.let { controller ->
            try {
                controller.navigate(destination.root, args, navOptions)
            } catch (e: IllegalArgumentException) {
                e.printStackTrace()
            }
        } ?: run {
            println("NavController is not set.")
        }
    }

    fun back() {
        navController?.popBackStack() ?: run {
            println("NavController is not set.")
        }
    }
}

enum class Destination(val root: Int) {
    LAUNCH(R.id.launchFragment),
    LOGIN(R.id.loginFragment),
    REGISTER(R.id.registerFragment)
}