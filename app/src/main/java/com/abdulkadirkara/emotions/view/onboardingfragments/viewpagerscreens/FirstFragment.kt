package com.abdulkadirkara.emotions.view.onboardingfragments.viewpagerscreens

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.abdulkadirkara.emotions.R
import com.abdulkadirkara.emotions.databinding.FragmentFirstBinding
import com.abdulkadirkara.emotions.view.onboardingfragments.ViewPagerHandler


class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private var viewPagerHandler: ViewPagerHandler? = null

    companion object {
        private const val PREFS_NAME = "myPrefs"
        private const val REMEMBER_ME_KEY = "remember_me"
        private const val ANIMATION_DURATION = 2000L
        private const val FINAL_ANIMATION_DURATION = 3000L
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewPagerHandler = parentFragment as? ViewPagerHandler
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        handleRememberMeSwitch()
        setButtonListeners()
        startTextAnimations()

        return binding.root
    }

    private fun handleRememberMeSwitch() {
        val sharedPreferences = requireActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val isRemembered = sharedPreferences.getBoolean(REMEMBER_ME_KEY, false)

        binding.switchRememberMe.isChecked = isRemembered
        if (isRemembered) {
            navigateToHome()
        }

        binding.switchRememberMe.setOnCheckedChangeListener { _, isChecked ->
            sharedPreferences.edit().putBoolean(REMEMBER_ME_KEY, isChecked).apply()
        }
    }

    private fun setButtonListeners() {
        binding.button.setOnClickListener {
            viewPagerHandler?.getViewPager()?.currentItem = 2
        }
        binding.buttonSkip.setOnClickListener {
            navigateToHome()
        }
    }

    private fun navigateToHome() {
        findNavController().navigate(R.id.action_onBoardingFragment_to_homeFragment, null,
            NavOptions.Builder().setPopUpTo(R.id.nav_graph, true).build())
    }

    private fun startTextAnimations() {
        val animatorSet = AnimatorSet().apply {
            playSequentially(
                createTextAnimationWithFadeOut(binding.textView1),
                createTextAnimationWithFadeOut(binding.textView2),
                createTextAnimationWithFadeOut(binding.textView3),
                createTextFadeInAnimation(binding.textView4)
            )
        }
        animatorSet.start()
    }

    private fun createTextAnimationWithFadeOut(textView: TextView): AnimatorSet {
        return AnimatorSet().apply {
            playSequentially(
                createTextFadeInAnimator(textView, ANIMATION_DURATION),
                createTextFadeOutAnimator(textView, ANIMATION_DURATION)
            )
        }
    }

    private fun createTextFadeInAnimator(textView: TextView, duration: Long): ObjectAnimator {
        textView.visibility = View.VISIBLE
        return ObjectAnimator.ofArgb(
            textView, "textColor",
            ContextCompat.getColor(requireContext(), android.R.color.transparent),
            ContextCompat.getColor(requireContext(), android.R.color.black)
        ).apply {
            this.duration = duration
        }
    }

    private fun createTextFadeOutAnimator(textView: TextView, duration: Long): ObjectAnimator {
        return ObjectAnimator.ofArgb(
            textView, "textColor",
            ContextCompat.getColor(requireContext(), android.R.color.black),
            ContextCompat.getColor(requireContext(), android.R.color.transparent)
        ).apply {
            this.duration = duration
        }
    }

    private fun createTextFadeInAnimation(textView: TextView): ObjectAnimator {
        return createTextFadeInAnimator(textView, FINAL_ANIMATION_DURATION)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}