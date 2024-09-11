package com.abdulkadirkara.emotions.view

import android.view.LayoutInflater
import android.view.ViewGroup
import com.abdulkadirkara.emotions.R
import com.abdulkadirkara.emotions.databinding.FragmentKindnessBinding

class KindnessFragment : BaseViewPagerFragment<FragmentKindnessBinding>() {

    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentKindnessBinding {
        return FragmentKindnessBinding.inflate(inflater, container, false)
    }
    override fun getTitles(): List<String> {
        return listOf(
            "Nezaket, başkalarının sırtındaki ağırlığı hafifletmek için elini uzatmaktır",
            "Nezaket, geri dönüşü olmayan bir yoldur; verdiğinde sana bir şekilde geri döner.",
            "Nezaket; duymadıklarımızı duymak, yapmadıklarımızı yapmak, görmediklerimizi görmek demektir.",
            "Nezaket, sevgiyi görünür kılar.",
            "Bir insanın değeri, başkalarına gösterdiği nezaketle ölçülür."
        )
    }

    override fun getTexts(): List<String> {
        return listOf(
            "—Joseph Joubert",
            "—Seneca",
            "—Mark Twain",
            "—Ralph Waldo Emerson",
            "—Albert Schweitzer"
        )
    }

    override fun getAnimations(): List<Int> {
        return listOf(
            R.raw.animsixteen,
            R.raw.animfive,
            R.raw.animfour,
            R.raw.animthree,
            R.raw.animtwo
        )
    }
}