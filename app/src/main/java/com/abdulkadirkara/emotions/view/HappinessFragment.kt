package com.abdulkadirkara.emotions.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.abdulkadirkara.emotions.R
import com.abdulkadirkara.emotions.adapter.ViewPagerAdapter
import com.abdulkadirkara.emotions.databinding.FragmentHappinessBinding


class HappinessFragment : Fragment() {

    private var _binding: FragmentHappinessBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHappinessBinding.inflate(inflater, container, false)

        // Veriler
        val titles = listOf(
            "Mutluluğu tatmanın tek bir yolu vardır: Başkalarına da mutluluk vermek.",
            "Mutlu bir yaşam, dingin bir aklın sonucudur.",
            "Mutluluk, insanın düşündüğü, söylediği ve yaptığı şeylerin uyum içinde olmasıdır.",
            "Mutlu olmak istiyorsan, kendini başkalarıyla karşılaştırmayı bırak.",
            "Mutluluk, sahip olduklarını sevmekle olur, istediğin her şeye sahip olmakla değil.",
            "Mutluluğun sırrı, yapılacak doğru şeyi bulmaktan çok, yapılan her şeyde mutluluğu bulmaktır."
        )

        val texts = listOf(
            "—John Stuart Mill",
            "—Marcus Aurelius",
            "—Mahatma Gandhi",
            "—Albert Einstein",
            "—Leo Tolstoy",
            "—Thomas Carlyle"
        )
        val animations = listOf(
            R.raw.animthree,
            R.raw.animfour,
            R.raw.animfive,
            R.raw.animseven,
            R.raw.animnine,
            R.raw.animten
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