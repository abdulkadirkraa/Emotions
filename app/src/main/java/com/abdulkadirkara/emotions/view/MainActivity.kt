package com.abdulkadirkara.emotions.view

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.abdulkadirkara.emotions.R
import com.abdulkadirkara.emotions.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        getNavControllerViaFragment()
    }

    private fun getNavControllerViaFragment() {
        val navHostFragmentView =
            supportFragmentManager.findFragmentById(binding.navHostFragment.id) as NavHostFragment
        val navController = navHostFragmentView.navController

        binding.bottomNavigationView.setupWithNavController(navController)
    }

    fun toggleBottomNavigationView(items: List<Int>) {
        if (items.isEmpty()) {
            hideBottomNavigationView()
        } else {
            clearAndInflateMenu()
            items.forEach { item ->
                when (item) {
                    R.id.happinessFragment -> addMenuItem(R.id.happinessFragment,"Happiness",R.drawable.ic_happiness)
                    R.id.optimismFragment -> addMenuItem(R.id.optimismFragment,"Optimism",R.drawable.ic_optimism)
                    R.id.kindnessFragment -> addMenuItem(R.id.kindnessFragment,"Kindness",R.drawable.ic_kindness)
                    R.id.givingFragment -> addMenuItem(R.id.givingFragment,"Giving",R.drawable.ic_giving)
                    R.id.respectFragment -> addMenuItem(
                        R.id.respectFragment,
                        "Respect",
                        R.drawable.ic_respect
                    )
                }
            }
            showBottomNavigationView()
        }
    }
    private fun hideBottomNavigationView() {
        binding.bottomNavigationView.visibility = View.GONE
    }
    private fun showBottomNavigationView() {
        binding.bottomNavigationView.visibility = View.VISIBLE
    }
    private fun clearAndInflateMenu() {
        binding.bottomNavigationView.menu.clear()
        binding.bottomNavigationView.inflateMenu(R.menu.bottom_nav_menu)
    }
    private fun addMenuItem(itemId: Int, title: String, iconResId: Int) {
        binding.bottomNavigationView.menu.add(
            Menu.NONE,
            itemId,
            Menu.NONE,
            title
        ).setIcon(iconResId)
    }
}