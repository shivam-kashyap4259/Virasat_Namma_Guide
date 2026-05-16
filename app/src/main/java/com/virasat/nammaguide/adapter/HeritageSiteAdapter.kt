package com.virasat.nammaguide.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.virasat.nammaguide.data.HeritageSite
import com.virasat.nammaguide.databinding.ItemHeritageSiteBinding

class HeritageSiteAdapter(
    private val onSiteClick: (HeritageSite) -> Unit
) : ListAdapter<HeritageSite, HeritageSiteAdapter.HeritageSiteViewHolder>(DiffCallback) {

    private var isKannadaSelected = false

    fun setLanguage(isKannada: Boolean) {
        this.isKannadaSelected = isKannada
        notifyItemRangeChanged(0, itemCount)
    }

    inner class HeritageSiteViewHolder(private val binding: ItemHeritageSiteBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(site: HeritageSite) {
            binding.apply {
                tvSiteName.text = if (isKannadaSelected) site.nameKn else site.nameEn
                tvSiteDescription.text = if (isKannadaSelected) site.descriptionKn.take(100) + "..."
                else site.descriptionEn.take(100) + "..."
                
                root.setOnClickListener {
                    onSiteClick(site)
                }
            }
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<HeritageSite>() {
        override fun areItemsTheSame(oldItem: HeritageSite, newItem: HeritageSite): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: HeritageSite, newItem: HeritageSite): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeritageSiteViewHolder {
        val binding = ItemHeritageSiteBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HeritageSiteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HeritageSiteViewHolder, position: Int) {
        val site = getItem(position)
        holder.bind(site)
    }
}
