package com.abdulkadirkara.emotions.view

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.abdulkadirkara.emotions.R
import com.abdulkadirkara.emotions.databinding.ActivityMainBinding
import com.abdulkadirkara.emotions.viewmodel.HomeFragmentViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: HomeFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.navHostFragment) as NavHostFragment

        NavigationUI.setupWithNavController(binding.bottomNavigationView, navHostFragment.navController)

        viewModel = ViewModelProvider(this)[HomeFragmentViewModel::class.java]

        with(viewModel) {
            isHappinessState.observe(this@MainActivity) { isChecked ->
                updateBottomNavMenu(isChecked, R.id.happinessFragment)
            }

            isKindnessState.observe(this@MainActivity) { isChecked ->
                updateBottomNavMenu(isChecked, R.id.kindnessFragment)
            }

            isOptimismState.observe(this@MainActivity) { isChecked ->
                updateBottomNavMenu(isChecked, R.id.optimismFragment)
            }

            isGivingState.observe(this@MainActivity) { isChecked ->
                updateBottomNavMenu(isChecked, R.id.givingFragment)
            }

            isRespectState.observe(this@MainActivity) { isChecked ->
                updateBottomNavMenu(isChecked, R.id.respectFragment)
            }

            isEgoState.observe(this@MainActivity) { isChecked ->
                if (!isChecked) {
                    binding.bottomNavigationView.visibility = View.VISIBLE
                } else {
                    binding.bottomNavigationView.visibility = View.GONE
                }
            }
        }
    }
    fun updateBottomNavMenu(isChecked: Boolean, itemId: Int) {
        val menu = binding.bottomNavigationView.menu

        if (isChecked) {
            if (menu.findItem(itemId) == null && menu.size() <= 4) {
                menu.add(Menu.NONE, itemId, Menu.NONE, getMenuTitle(itemId)).setIcon(getMenuIcon(itemId))
            }
        } else {
            menu.removeItem(itemId)
        }
    }

    private fun getMenuTitle(itemId: Int): String {
        return when (itemId) {
            R.id.happinessFragment -> "Happiness"
            R.id.kindnessFragment -> "Kindess"
            R.id.optimismFragment -> "Optimism"
            R.id.givingFragment -> "Giving"
            R.id.respectFragment -> "Respect"
            else -> ""
        }
    }

    private fun getMenuIcon(itemId: Int): Int {
        return when (itemId) {
            R.id.happinessFragment -> R.drawable.ic_happiness
            R.id.kindnessFragment -> R.drawable.ic_kindness
            R.id.optimismFragment -> R.drawable.ic_optimism
            R.id.givingFragment -> R.drawable.ic_giving
            R.id.respectFragment -> R.drawable.ic_respect
            else -> 0
        }
    }
}