package com.sarvesh.trending.ui.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.sarvesh.trending.R
import com.sarvesh.trending.model.TrendingListItem
import com.sarvesh.trending.databinding.ItemTrendingBinding
import com.sarvesh.trending.model.BuiltBy


class TrendingAdapter : RecyclerView.Adapter<TrendingViewHolder>(), Filterable {

    private var items = ArrayList<TrendingListItem>()
    private var mFilteredViewList = ArrayList<TrendingListItem>()

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: ArrayList<TrendingListItem>) {
        this.items.clear()
        this.mFilteredViewList.clear()
        this.items.addAll(items)
        this.mFilteredViewList.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingViewHolder {
        val binding: ItemTrendingBinding =
            ItemTrendingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TrendingViewHolder(binding)
    }

    override fun getItemCount(): Int = mFilteredViewList.size

    override fun onBindViewHolder(holder: TrendingViewHolder, position: Int) =
        holder.bind(mFilteredViewList[position])


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()
                if (charString.isEmpty()) {
                    mFilteredViewList = items
                } else {
                    val serviceDataArrayList = ArrayList<TrendingListItem>()
                    for (service in mFilteredViewList) {
                        if (service.repositoryName?.toLowerCase().toString()
                                .contains(charString) || service.username?.toLowerCase().toString()
                                .contains(charString)
                        ) {
                            serviceDataArrayList.add(service)
                        }
                    }
                    mFilteredViewList = serviceDataArrayList
                }
                val filterResults = FilterResults()
                filterResults.values = mFilteredViewList
                return filterResults
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun publishResults(constraint: CharSequence, filterResults: FilterResults) {
                mFilteredViewList = filterResults.values as ArrayList<TrendingListItem>
                notifyDataSetChanged()
            }
        }
    }


}

class TrendingViewHolder(private val itemBinding: ItemTrendingBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {

    @SuppressLint("SetTextI18n")
    fun bind(item: TrendingListItem) {
        itemBinding.listItem = item
        if (item.languageColor != null)
            itemBinding.dot.background.current.setTint(Color.parseColor(item.languageColor))
        itemBinding.selectCard.setOnClickListener {
            item.isSelected = !item.isSelected
            selectedCard(item.isSelected)
        }
        selectedCard(item.isSelected)
        val adapter = BuiltByAdapter()
        itemBinding.builtRecyclerview.adapter = adapter
        adapter.setItems(item.builtBy as ArrayList<BuiltBy>)
    }

    fun selectedCard(isSelect: Boolean) {
        if (isSelect)
            itemBinding.selectCard.setBackgroundColor(
                ContextCompat.getColor(
                    itemView.context,
                    R.color.colorAccent
                )
            )
        else
            itemBinding.selectCard.setBackgroundColor(
                ContextCompat.getColor(
                    itemView.context,
                    R.color.white
                )
            )

    }

}





