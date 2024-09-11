package com.abdulkadirkara.emotions.view

import android.view.LayoutInflater
import android.view.ViewGroup
import com.abdulkadirkara.emotions.R
import com.abdulkadirkara.emotions.databinding.FragmentHappinessBinding


class HappinessFragment : BaseViewPagerFragment<FragmentHappinessBinding>() {

    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentHappinessBinding {
        return FragmentHappinessBinding.inflate(inflater, container, false)
    }

    override fun getTitles(): List<String> {
        return listOf(
            "Mutluluğu tatmanın tek bir yolu vardır: Başkalarına da mutluluk vermek.",
            "Mutlu bir yaşam, dingin bir aklın sonucudur.",
            "Mutluluk, insanın düşündüğü, söylediği ve yaptığı şeylerin uyum içinde olmasıdır.",
            "Mutlu olmak istiyorsan, kendini başkalarıyla karşılaştırmayı bırak.",
            "Mutluluk, sahip olduklarını sevmekle olur, istediğin her şeye sahip olmakla değil.",
            "Mutluluğun sırrı, yapılacak doğru şeyi bulmaktan çok, yapılan her şeyde mutluluğu bulmaktır."
        )
    }

    override fun getTexts(): List<String> {
        return listOf(
            "—John Stuart Mill",
            "—Marcus Aurelius",
            "—Mahatma Gandhi",
            "—Albert Einstein",
            "—Leo Tolstoy",
            "—Thomas Carlyle"
        )
    }

    override fun getAnimations(): List<Int> {
        return listOf(
            R.raw.animthree,
            R.raw.animfour,
            R.raw.animfive,
            R.raw.animseven,
            R.raw.animnine,
            R.raw.animten
        )
    }
}
