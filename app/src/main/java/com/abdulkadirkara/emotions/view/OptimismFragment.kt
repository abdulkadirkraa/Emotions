package com.abdulkadirkara.emotions.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.abdulkadirkara.emotions.R
import com.abdulkadirkara.emotions.adapter.ViewPagerAdapter
import com.abdulkadirkara.emotions.databinding.FragmentOptimismBinding


class OptimismFragment : Fragment() {

    private var _binding: FragmentOptimismBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentOptimismBinding.inflate(inflater, container, false)
        // Veriler
        val titles = listOf(
            "Her şeyin iyi olacağına inanmayan, hiçbir şey başaramaz.",
            "İyimserler her fırtınada güneşi görür, kötümserler her güneşte bir fırtına bekler.",
            "İyimser insan, her krizde bir fırsat; kötümser insan ise her fırsatta bir kriz görür.",
            "İyimserlik, insan ruhunun bir zaferidir.",
            "Güneşin batışına bakın, çünkü ertesi sabah yeniden doğar."
        )

        val texts = listOf(
            "—Helen Keller",
            "—William Arthur Ward",
            "—Winston Churchill",
            "—Helen Keller",
            "—Hafez"
        )
        val animations = listOf(
            R.raw.animeleven,
            R.raw.animtwelve,
            R.raw.animthirteen,
            R.raw.animfourteen,
            R.raw.animfiveteen
        )

        // ViewPager ve Adapter bağlama
        val viewPager = binding.viewPager
        val adapter = ViewPagerAdapter(titles, texts, animations)
        viewPager.adapter = adapter

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}