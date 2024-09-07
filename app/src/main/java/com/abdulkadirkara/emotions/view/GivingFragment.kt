package com.abdulkadirkara.emotions.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.abdulkadirkara.emotions.R
import com.abdulkadirkara.emotions.adapter.ViewPagerAdapter
import com.abdulkadirkara.emotions.databinding.FragmentGivingBinding

class GivingFragment : Fragment() {

    private var _binding: FragmentGivingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentGivingBinding.inflate(inflater, container, false)

        // Veriler
        val titles = listOf(
            "Vermek, alanın ruhunu beslediği kadar verenin ruhunu da büyütür.",
            "Sevgi vermektir, almak değil.",
            "Başkalarına verebileceğimiz en büyük hediye zamanımızdır.",
            "Gerçek zenginlik, verdiklerinle ölçülür."
        )

        val texts = listOf(
            "—Maya Angelou",
            "—Victor Hugo",
            "—Rick Warren",
            "—Walt Whitman"
        )
        val animations = listOf(
            R.raw.animeleven,
            R.raw.animten,
            R.raw.animnine,
            R.raw.animseven
        )

        // ViewPager ve Adapter bağlama
        val viewPager = binding.viewPager
        val adapter = ViewPagerAdapter(titles, texts, animations)
        viewPager.adapter = adapter

        // Page margin ve adjacent page'in görünmesini sağlamak için ayarlar
        viewPager.offscreenPageLimit = 3
        viewPager.setPageTransformer { page, position ->
            val scaleFactor = 0.85f.coerceAtLeast(1 - kotlin.math.abs(position))
            page.scaleY = scaleFactor
        }

        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}