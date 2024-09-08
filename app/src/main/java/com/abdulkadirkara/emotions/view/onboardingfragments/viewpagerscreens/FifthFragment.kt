package com.abdulkadirkara.emotions.view.onboardingfragments.viewpagerscreens

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.VideoView
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.abdulkadirkara.emotions.R
import com.abdulkadirkara.emotions.databinding.FragmentFifthBinding

class FifthFragment : Fragment() {

    private var _binding: FragmentFifthBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFifthBinding.inflate(inflater, container, false)

        setupVideoPlayer()
        setupNavigation()

        return binding.root
    }

    private fun setupVideoPlayer() {
        val videoUri = getVideoUri(R.raw.videofour)
        binding.videoView.apply {
            setVideoURI(videoUri)
            setupMediaController(this)
            setOnPreparedListener {
                start()
            }
        }
    }

    private fun getVideoUri(videoResId: Int): Uri {
        val packageName = "android.resource://${requireContext().packageName}/$videoResId"
        return Uri.parse(packageName)
    }

    private fun setupMediaController(videoView: VideoView) {
        val mediaController = MediaController(requireContext()).apply {
            setAnchorView(videoView)
        }
        videoView.setMediaController(mediaController)
    }

    private fun setupNavigation() {
        binding.button.setOnClickListener {
            navigateToHome()
        }
    }

    private fun navigateToHome() {
        findNavController().navigate(R.id.action_onBoardingFragment_to_homeFragment, null,
            NavOptions.Builder().setPopUpTo(R.id.nav_graph, true).build())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}