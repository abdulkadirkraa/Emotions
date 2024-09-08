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
import com.abdulkadirkara.emotions.databinding.FragmentSecondBinding
import com.abdulkadirkara.emotions.view.onboardingfragments.ViewPagerHandler

class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
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
        _binding = FragmentSecondBinding.inflate(inflater, container, false)

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
        val packageName = "android.resource://${requireContext().packageName}/${R.raw.videoone}"
        return Uri.parse(packageName)
    }

    private fun setButtonClickListener() {
        binding.button.setOnClickListener {
            viewPagerHandler?.getViewPager()?.currentItem = 3 // Sayfa değiştirme işlemi
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}