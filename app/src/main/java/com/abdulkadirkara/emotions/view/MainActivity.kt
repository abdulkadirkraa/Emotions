package com.abdulkadirkara.emotions.view

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.abdulkadirkara.emotions.R
import com.abdulkadirkara.emotions.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
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

        binding.apply {
            setContentView(root)
            setSupportActionBar(toolBar)
            navController = (supportFragmentManager
                .findFragmentById(navHostFragment.id) as NavHostFragment)
                .navController
        }

    }

    fun toggleBottomNavigationView(items: List<Int>) {
        if (items.isEmpty()) {
            binding.bottomNavigationView.visibility = View.GONE
        } else {
            binding.bottomNavigationView.menu.clear()
            binding.bottomNavigationView.inflateMenu(R.menu.bottom_nav_menu) // Inflate the default menu
            items.forEach { item ->
                when (item) {
                    R.id.happinessFragment -> binding.bottomNavigationView.menu.add(
                        Menu.NONE,
                        R.id.happinessFragment,
                        Menu.NONE,
                        "Happiness"
                    ).setIcon(R.drawable.ic_happiness)

                    R.id.optimismFragment -> binding.bottomNavigationView.menu.add(
                        Menu.NONE,
                        R.id.optimismFragment,
                        Menu.NONE,
                        "Optimism"
                    ).setIcon(R.drawable.ic_optimism)

                    R.id.kindnessFragment -> binding.bottomNavigationView.menu.add(
                        Menu.NONE,
                        R.id.kindnessFragment,
                        Menu.NONE,
                        "Kindness"
                    ).setIcon(R.drawable.ic_kindness)

                    R.id.givingFragment -> binding.bottomNavigationView.menu.add(
                        Menu.NONE,
                        R.id.givingFragment,
                        Menu.NONE,
                        "Giving"
                    ).setIcon(R.drawable.ic_giving)

                    R.id.respectFragment -> binding.bottomNavigationView.menu.add(
                        Menu.NONE,
                        R.id.respectFragment,
                        Menu.NONE,
                        "Respect"
                    ).setIcon(R.drawable.ic_respect)
                }
            }
            binding.bottomNavigationView.visibility = View.VISIBLE
        }
    }
}