package com.abdulkadirkara.emotions.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.abdulkadirkara.emotions.R
import com.abdulkadirkara.emotions.adapter.ViewPagerAdapter
import com.abdulkadirkara.emotions.databinding.FragmentRespectBinding

class RespectFragment : Fragment() {

    private var _binding: FragmentRespectBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentRespectBinding.inflate(inflater, container, false)
        // Veriler
        val titles = listOf(
            "Başkalarına gösterdiğiniz saygı, size gösterilecek saygının aynasıdır.",
            "Saygı görmek istiyorsan, herkese olduğu gibi davran; ne fazla ne eksik.",
            "Kendine saygı duymayan bir insan başkalarına asla saygı gösteremez.",
            "Saygı, iki insan arasındaki mesafeyi azaltan görünmez bir köprüdür.",
            "Saygı duyulmak için önce saygı göstermeliyiz."
        )

        val texts = listOf(
            "—Anonymous",
            "—Johann Wolfgang von Goethe",
            "—Albert Camus",
            "—Leo Tolstoy",
            "—Albert Einstein"
        )
        val animations = listOf(
            R.raw.animsixteen,
            R.raw.animfiveteen,
            R.raw.animfourteen,
            R.raw.animthirteen,
            R.raw.animtwelve
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