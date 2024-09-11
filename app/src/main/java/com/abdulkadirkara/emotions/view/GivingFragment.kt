package com.abdulkadirkara.emotions.view

import android.view.LayoutInflater
import android.view.ViewGroup
import com.abdulkadirkara.emotions.R
import com.abdulkadirkara.emotions.databinding.FragmentGivingBinding

class GivingFragment : BaseViewPagerFragment<FragmentGivingBinding>() {

    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentGivingBinding {
        return FragmentGivingBinding.inflate(inflater, container, false)
    }

    override fun getTitles(): List<String> {
        return listOf(
            "Vermek, alanın ruhunu beslediği kadar verenin ruhunu da büyütür.",
            "Sevgi vermektir, almak değil.",
            "Başkalarına verebileceğimiz en büyük hediye zamanımızdır.",
            "Gerçek zenginlik, verdiklerinle ölçülür."
        )
    }

    override fun getTexts(): List<String> {
        return listOf(
            "—Maya Angelou",
            "—Victor Hugo",
            "—Rick Warren",
            "—Walt Whitman"
        )
    }

    override fun getAnimations(): List<Int> {
        return listOf(
            R.raw.animeleven,
            R.raw.animten,
            R.raw.animnine,
            R.raw.animseven
        )
    }
}
