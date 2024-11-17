package com.example.myapplication.screens.launch

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.base.BaseFragment
import com.example.myapplication.databinding.FragmentLaunchBinding
import com.example.myapplication.util.Destination
import com.example.myapplication.util.NavigationManager

class LaunchFragment : BaseFragment() {

    lateinit var binding: FragmentLaunchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FragmentLaunchBinding.inflate(layoutInflater)

        binding.registerBtn.setOnClickListener {
            NavigationManager.navigate(Destination.REGISTER)
        }

        binding.loginBtn.setOnClickListener {
            NavigationManager.navigate(Destination.LOGIN)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }
}