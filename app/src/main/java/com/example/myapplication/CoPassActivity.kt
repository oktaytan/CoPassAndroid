package com.example.myapplication

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.myapplication.databinding.ActivityCopassBinding
import com.example.myapplication.util.Destination
import com.example.myapplication.util.NavigationManager

class CoPassActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCopassBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCopassBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
                .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        NavigationManager.setNavController(navHostFragment.navController)

        val isRegistered = checkIfUserIsRegistered()

        if (isRegistered) {
            NavigationManager.navigate(Destination.LOGIN)
        } else {
            NavigationManager.navigate(Destination.LAUNCH)
        }

    }

    private fun checkIfUserIsRegistered(): Boolean {
        val sharedPreferences = getSharedPreferences("UserPref", MODE_PRIVATE)
        return sharedPreferences.getBoolean("isRegistered", false)
    }
}