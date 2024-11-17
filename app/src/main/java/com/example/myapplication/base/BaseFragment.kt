package com.example.myapplication.base

import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.util.NavigationManager

open class BaseFragment: Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val inflater = TransitionInflater.from(requireContext())
        enterTransition = inflater.inflateTransition(R.transition.slide_in)
        exitTransition = inflater.inflateTransition(R.transition.slide_out)
    }

    public fun back() {
        NavigationManager.back()
    }
}