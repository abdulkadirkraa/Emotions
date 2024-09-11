package com.abdulkadirkara.emotions.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abdulkadirkara.emotions.databinding.ViewpagerItemBinding

class ViewPagerAdapter(
    private val titles: List<String>,
    private val texts: List<String>,
    private val animations: List<Int>
) : RecyclerView.Adapter<ViewPagerAdapter.ViewHolder>() {

    class ViewHolder(val binding: ViewpagerItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewpagerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding) {
            title.text = titles[position]
            text.text = texts[position]
            try {
                lottieAnimation.setAnimation(animations[position])
                lottieAnimation.playAnimation()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun getItemCount(): Int {
        return titles.size
    }
}
