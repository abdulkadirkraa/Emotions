package com.abdulkadirkara.emotions.view

import android.view.LayoutInflater
import android.view.ViewGroup
import com.abdulkadirkara.emotions.R
import com.abdulkadirkara.emotions.databinding.FragmentOptimismBinding


class OptimismFragment : BaseViewPagerFragment<FragmentOptimismBinding>() {

    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentOptimismBinding {
        return FragmentOptimismBinding.inflate(inflater, container, false)
    }
    override fun getTitles(): List<String> {
        return listOf(
            "Her şeyin iyi olacağına inanmayan, hiçbir şey başaramaz.",
            "İyimserler her fırtınada güneşi görür, kötümserler her güneşte bir fırtına bekler.",
            "İyimser insan, her krizde bir fırsat; kötümser insan ise her fırsatta bir kriz görür.",
            "İyimserlik, insan ruhunun bir zaferidir.",
            "Güneşin batışına bakın, çünkü ertesi sabah yeniden doğar."
        )
    }

    override fun getTexts(): List<String> {
        return listOf(
            "—Helen Keller",
            "—William Arthur Ward",
            "—Winston Churchill",
            "—Helen Keller",
            "—Hafez"
        )
    }

    override fun getAnimations(): List<Int> {
        return listOf(
            R.raw.animeleven,
            R.raw.animtwelve,
            R.raw.animthirteen,
            R.raw.animfourteen,
            R.raw.animfiveteen
        )
    }
}