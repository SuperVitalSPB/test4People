package com.test.people.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.test.people.di.App.Companion.PARAM_SOURCE_FRAGMENT
import com.test.people.ui.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), INavigateSort {

    private lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController
    lateinit var navView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navView = binding.navView

        navController = findNavController(R.id.nav_host_fragment_activity_main)

        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.navigation_popular, R.id.navigation_favorites, R.id.navigation_sort)
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

    }

    override fun navigateSort(sourceFragment: SourceFragment) {
        navView.selectedItemId = R.id.navigation_sort
        navController.navigate(R.id.navigation_sort, Bundle()
                                                        .apply { putInt(PARAM_SOURCE_FRAGMENT, sourceFragment.id)})
    }

}