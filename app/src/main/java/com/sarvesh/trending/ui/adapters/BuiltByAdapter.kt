package com.sarvesh.trending.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.sarvesh.trending.databinding.ItemBuildbyBinding
import com.sarvesh.trending.model.BuiltBy


class BuiltByAdapter : RecyclerView.Adapter<BuildByViewHolder>(){

    private var items = ArrayList<BuiltBy>()


    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: ArrayList<BuiltBy>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuildByViewHolder {
        val binding: ItemBuildbyBinding =
            ItemBuildbyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BuildByViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: BuildByViewHolder, position: Int) =
        holder.bind(items[position])
}

class BuildByViewHolder(private val itemBinding: ItemBuildbyBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {

    private lateinit var builtByListItem: BuiltBy

    @SuppressLint("SetTextI18n")
    fun bind(item: BuiltBy) {
        this.builtByListItem = item

        Glide.with(itemBinding.root)
            .load(item.avatar)
            .transform(CircleCrop())
            .into(itemBinding.image)
    }




}





