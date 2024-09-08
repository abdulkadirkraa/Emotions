package com.abdulkadirkara.emotions.view.onboardingfragments.viewpagerscreens

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import com.abdulkadirkara.emotions.R
import com.abdulkadirkara.emotions.databinding.FragmentFourthBinding
import com.abdulkadirkara.emotions.view.onboardingfragments.ViewPagerHandler


class FourthFragment : Fragment() {
    private var _binding: FragmentFourthBinding? = null
    private val binding get() = _binding!!

    private var viewPagerHandler: ViewPagerHandler? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewPagerHandler = parentFragment as? ViewPagerHandler
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFourthBinding.inflate(inflater, container, false)

        setupVideoPlayer()
        setButtonClickListener()

        return binding.root
    }

    private fun setupVideoPlayer() {
        val videoUri = getVideoUri()
        binding.videoView.apply {
            setVideoURI(videoUri)
            setMediaController(MediaController(requireContext()))
        }
    }

    private fun getVideoUri(): Uri {
        val packageName = "android.resource://${requireContext().packageName}/${R.raw.videothree}"
        return Uri.parse(packageName)
    }

    private fun setButtonClickListener() {
        binding.button.setOnClickListener {
            viewPagerHandler?.getViewPager()?.currentItem = 5
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}