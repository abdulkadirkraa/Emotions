package com.abdulkadirkara.emotions.view

import android.view.LayoutInflater
import android.view.ViewGroup
import com.abdulkadirkara.emotions.R
import com.abdulkadirkara.emotions.databinding.FragmentRespectBinding

class RespectFragment : BaseViewPagerFragment<FragmentRespectBinding>() {

    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentRespectBinding {
        return FragmentRespectBinding.inflate(inflater, container, false)
    }
    override fun getTitles(): List<String> {
        return listOf(
            "Başkalarına gösterdiğiniz saygı, size gösterilecek saygının aynasıdır.",
            "Saygı görmek istiyorsan, herkese olduğu gibi davran; ne fazla ne eksik.",
            "Kendine saygı duymayan bir insan başkalarına asla saygı gösteremez.",
            "Saygı, iki insan arasındaki mesafeyi azaltan görünmez bir köprüdür.",
            "Saygı duyulmak için önce saygı göstermeliyiz."
        )
    }

    override fun getTexts(): List<String> {
        return listOf(
            "—Anonymous",
            "—Johann Wolfgang von Goethe",
            "—Albert Camus",
            "—Leo Tolstoy",
            "—Albert Einstein"
        )
    }

    override fun getAnimations(): List<Int> {
        return listOf(
            R.raw.animsixteen,
            R.raw.animfiveteen,
            R.raw.animfourteen,
            R.raw.animthirteen,
            R.raw.animtwelve
        )
    }
}