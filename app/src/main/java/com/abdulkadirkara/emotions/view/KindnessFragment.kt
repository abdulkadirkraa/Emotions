package com.abdulkadirkara.emotions.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.abdulkadirkara.emotions.R
import com.abdulkadirkara.emotions.adapter.ViewPagerAdapter
import com.abdulkadirkara.emotions.databinding.FragmentKindnessBinding

class KindnessFragment : Fragment() {

    private var _binding: FragmentKindnessBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentKindnessBinding.inflate(inflater, container, false)
        // Veriler
        val titles = listOf(
            "Nezaket, başkalarının sırtındaki ağırlığı hafifletmek için elini uzatmaktır",
            "Nezaket, geri dönüşü olmayan bir yoldur; verdiğinde sana bir şekilde geri döner.",
            "Nezaket; duymadıklarımızı duymak, yapmadıklarımızı yapmak, görmediklerimizi görmek demektir.",
            "Nezaket, sevgiyi görünür kılar.",
            "Bir insanın değeri, başkalarına gösterdiği nezaketle ölçülür."
        )

        val texts = listOf(
            "—Joseph Joubert",
            "—Seneca",
            "—Mark Twain",
            "—Ralph Waldo Emerson",
            "—Albert Schweitzer"
        )
        val animations = listOf(
            R.raw.animsixteen,
            R.raw.animfive,
            R.raw.animfour,
            R.raw.animthree,
            R.raw.animtwo
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