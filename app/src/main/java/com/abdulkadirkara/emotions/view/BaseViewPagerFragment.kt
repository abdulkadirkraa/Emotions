package com.abdulkadirkara.emotions.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import androidx.viewpager2.widget.ViewPager2
import com.abdulkadirkara.emotions.R
import com.abdulkadirkara.emotions.adapter.ViewPagerAdapter

abstract class BaseViewPagerFragment<VB : ViewBinding> : BaseFragment<VB>() {

    abstract fun getTitles(): List<String>
    abstract fun getTexts(): List<String>
    abstract fun getAnimations(): List<Int>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = super.onCreateView(inflater, container, savedInstanceState)

        val viewPager = binding.root.findViewById<ViewPager2>(R.id.viewPager)
        val adapter = ViewPagerAdapter(getTitles(), getTexts(), getAnimations())
        viewPager.adapter = adapter

        viewPager.offscreenPageLimit = 3
        viewPager.setPageTransformer { page, position ->
            val scaleFactor = 0.85f.coerceAtLeast(1 - kotlin.math.abs(position))
            page.scaleY = scaleFactor
        }

        return view
    }
}
