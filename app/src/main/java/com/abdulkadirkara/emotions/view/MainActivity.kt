package com.abdulkadirkara.emotions.view

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
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
    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[HomeFragmentViewModel::class.java]

        navHostFragment = supportFragmentManager
            .findFragmentById(R.id.navHostFragment) as NavHostFragment

        val navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)

        binding.bottomNavigationView.visibility = View.GONE

        // Arka plan rengini ayarla
        val window = window
        val navBarColor = ContextCompat.getColor(this, R.color.nav_bar_color)
        window.navigationBarColor = navBarColor

        // BottomNavigationView ayarları
        binding.bottomNavigationView.background = ContextCompat.getDrawable(this, R.drawable.bottom_nav_background)

        val aniationDrawable: AnimationDrawable = binding.navHostFragment.background as AnimationDrawable
        aniationDrawable.setEnterFadeDuration(1000)
        aniationDrawable.setExitFadeDuration(3000)
        aniationDrawable.start()

        observeViewModel()

    }

    private fun observeViewModel() {
        with(viewModel) {
            isHappinessState.observe(this@MainActivity) { isChecked ->
                Log.e("EGO","MainActivity-observeViewModel-isHappinessState $isChecked")
                updateBottomNavMenu(isChecked, R.id.happinessFragment)
            }

            isKindnessState.observe(this@MainActivity) { isChecked ->
                Log.e("EGO","MainActivity-observeViewModel-isKindnessState $isChecked")
                updateBottomNavMenu(isChecked, R.id.kindnessFragment)
            }

            isOptimismState.observe(this@MainActivity) { isChecked ->
                Log.e("EGO","MainActivity-observeViewModel-isOptimismState $isChecked")
                updateBottomNavMenu(isChecked, R.id.optimismFragment)
            }

            isGivingState.observe(this@MainActivity) { isChecked ->
                Log.e("EGO","MainActivity-observeViewModel-isGivingState $isChecked")
                updateBottomNavMenu(isChecked, R.id.givingFragment)
            }

            isRespectState.observe(this@MainActivity) { isChecked ->
                Log.e("EGO","MainActivity-observeViewModel-isRespectState $isChecked")
                updateBottomNavMenu(isChecked, R.id.respectFragment)
            }

            isEgoState.observe(this@MainActivity) { isChecked ->
                Log.e("EGO","MainActivity-observeViewModel-isEgoState $isChecked")
                if (!isChecked) {
                    binding.bottomNavigationView.visibility = View.VISIBLE
                    Log.e("EGO","MainActivity-observeViewModel-bottomnav-VISIBLE")
                    updateBottomNavMenu(true, R.id.homeFragment)
                } else {
                    binding.bottomNavigationView.visibility = View.GONE
                    Log.e("EGO","MainActivity-observeViewModel-bottomnav-GONE")
                    resetBottomNavMenu()
                }
            }
        }
    }

    private fun updateBottomNavMenu(isChecked: Boolean, itemId: Int) {
        val menu = binding.bottomNavigationView.menu
        if (isChecked && menu.findItem(itemId) == null && menu.size() < 5) {
            menu.add(Menu.NONE, itemId, Menu.NONE, getMenuTitle(itemId))
                .setIcon(getMenuIcon(itemId))

            Log.e("EGO","MainActivity-updateBottomNavMenu-add ${getMenuTitle(itemId)}")
            // Sync with NavController when new item is added
            NavigationUI.setupWithNavController(binding.bottomNavigationView, navHostFragment.navController)
        } else if (!isChecked) {
            menu.removeItem(itemId)
        }
    }

    private fun resetBottomNavMenu() {
        Log.e("EGO","MainActivity-resetBottomNavMenu")
        val menu = binding.bottomNavigationView.menu
        menu.clear()
        menu.add(Menu.NONE, R.id.homeFragment, Menu.NONE, "Home")
            .setIcon(R.drawable.ic_home)
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