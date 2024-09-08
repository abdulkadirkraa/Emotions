package com.abdulkadirkara.emotions.view.onboardingfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.abdulkadirkara.emotions.adapter.OnBoardingViewPagerAdapter
import com.abdulkadirkara.emotions.databinding.FragmentOnBoardingBinding
import com.abdulkadirkara.emotions.view.onboardingfragments.viewpagerscreens.FifthFragment
import com.abdulkadirkara.emotions.view.onboardingfragments.viewpagerscreens.FirstFragment
import com.abdulkadirkara.emotions.view.onboardingfragments.viewpagerscreens.FourthFragment
import com.abdulkadirkara.emotions.view.onboardingfragments.viewpagerscreens.SecondFragment
import com.abdulkadirkara.emotions.view.onboardingfragments.viewpagerscreens.ThirdFragment

interface ViewPagerHandler {
    fun getViewPager(): ViewPager2
}

class OnBoardingFragment : Fragment(), ViewPagerHandler {

    private var _binding: FragmentOnBoardingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnBoardingBinding.inflate(inflater, container, false)

        setupViewPager()
        setupDotsIndicator()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun getViewPager(): ViewPager2 {
        return binding.viewpager
    }

    private fun setupViewPager() {
        val adapter = OnBoardingViewPagerAdapter(
            getFragmentList(),
            requireActivity().supportFragmentManager,
            lifecycle
        )
        binding.viewpager.adapter = adapter
    }

    private fun setupDotsIndicator() {
        binding.dotsIndicator.attachTo(binding.viewpager)
    }

    private fun getFragmentList(): ArrayList<Fragment> {
        return arrayListOf(
            FirstFragment(),
            SecondFragment(),
            ThirdFragment(),
            FourthFragment(),
            FifthFragment()
        )
    }
}