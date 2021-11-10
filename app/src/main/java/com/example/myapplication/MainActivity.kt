package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
private const val ARG_PARAM1 = "param1"
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        val navController = navHostFragment.navController

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when(destination.id) {
                R.id.rechercheFragment2 -> Log.d("NAV", "recherche")
                R.id.topFragment2 -> Log.d("NAV", "Top")

                else -> Log.wtf("NAV", "Showing Another Fragment")
            }
        }
        val bottomMenu = findViewById<BottomNavigationView>(R.id.bottomBar)
        NavigationUI.setupWithNavController(bottomMenu,navController)


        binding.searchButton.setOnClickListener {
            binding.searchBar.isVisible = true




                binding.searchBar.setOnSearchClickListener(){

                }


        }
    /*
        private fun onQueryTextSubmit(s: String?): Boolean {
        return false
         }
    private fun onQueryTextChange(s: String?): Boolean {
        return false
    }
*/


        fun tabSearch(){

        }
    }
}