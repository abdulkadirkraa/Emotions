package com.abdulkadirkara.emotions.view

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavOptions
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
    private var currentItemIndex: Int = 0 // Geçerli öğenin pozisyonu

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

        val aniationDrawable: AnimationDrawable = binding.navHostFragment.background as AnimationDrawable
        aniationDrawable.setEnterFadeDuration(1000)
        aniationDrawable.setExitFadeDuration(3000)
        aniationDrawable.start()

        // Menü öğesi tıklamalarını dinleme
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            val newItemIndex = getItemIndex(item.itemId) // Tıklanan öğenin pozisyonunu al
            if (newItemIndex != -1) {
                val direction = getTransitionDirection(currentItemIndex, newItemIndex) // Yönü hesapla
                currentItemIndex = newItemIndex // Geçerli öğeyi güncelle
                // `NavController`'a geçişi yönetmesini söyle
                navHostFragment.navController.navigate(item.itemId, null, getNavOptions(direction))
            }
            true
        }

        observeViewModel()

    }
    private fun getNavOptions(direction: Int): NavOptions {
        return if (direction == 1) {
            NavOptions.Builder()
                .setEnterAnim(R.anim.slide_in_right)
                .setExitAnim(R.anim.slide_out_left)
                .setPopEnterAnim(R.anim.slide_in_left)
                .setPopExitAnim(R.anim.slide_out_right)
                .build()
        } else {
            NavOptions.Builder()
                .setEnterAnim(R.anim.slide_in_left)
                .setExitAnim(R.anim.slide_out_right)
                .setPopEnterAnim(R.anim.slide_in_right)
                .setPopExitAnim(R.anim.slide_out_left)
                .build()
        }
    }

    private fun switchFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()

        // Animasyonları ekleyin
        transaction.setCustomAnimations(
            R.anim.slide_in_right,  // Yeni fragment'in giriş animasyonu
            R.anim.slide_out_left,  // Mevcut fragment'in çıkış animasyonu
            R.anim.slide_in_left,   // Geri dönüşte giriş animasyonu
            R.anim.slide_out_right  // Geri dönüşte çıkış animasyonu
        )

        // Fragment'i değiştirin
        transaction.replace(R.id.navHostFragment, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
    // Fragment'i dinamik yönle değiştir
    private fun switchFragment(fragment: Fragment, direction: Int) {
        val transaction = supportFragmentManager.beginTransaction()

        // Geçiş animasyonlarını yönlendirme
        if (direction == 1) {
            // Sağdan sola geçiş
            transaction.setCustomAnimations(
                R.anim.slide_in_right,
                R.anim.slide_out_left,
                R.anim.slide_in_left,
                R.anim.slide_out_right
            )
        } else if (direction == -1) {
            // Soldan sağa geçiş
            transaction.setCustomAnimations(
                R.anim.slide_in_left,
                R.anim.slide_out_right,
                R.anim.slide_in_right,
                R.anim.slide_out_left
            )
        }

        transaction.replace(R.id.navHostFragment, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    // Tıklanan öğenin pozisyonunu belirle
    private fun getItemIndex(itemId: Int): Int {
        return when (itemId) {
            R.id.happinessFragment -> 0
            R.id.kindnessFragment -> 1
            R.id.optimismFragment -> 2
            R.id.givingFragment -> 3
            R.id.respectFragment -> 4
            else -> -1
        }
    }

    // İki öğe arasındaki yönü belirle
    private fun getTransitionDirection(currentIndex: Int, newIndex: Int): Int {
        return if (newIndex > currentIndex) 1 else -1
    }

    // ItemId'ye göre fragment'ı getir
    private fun getFragmentForItem(itemId: Int): Fragment {
        return when (itemId) {
            R.id.happinessFragment -> HappinessFragment()
            R.id.kindnessFragment -> KindnessFragment()
            R.id.optimismFragment -> OptimismFragment()
            R.id.givingFragment -> GivingFragment()
            R.id.respectFragment -> RespectFragment()
            else -> HomeFragment()
        }
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