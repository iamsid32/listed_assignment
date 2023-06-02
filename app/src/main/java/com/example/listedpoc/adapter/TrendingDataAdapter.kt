package com.example.listedpoc.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.listedpoc.R
import com.example.listedpoc.model.TrendingData

class TrendingDataAdapter(
    private val items: List<TrendingData>,
    private val context:Context,
    private val onItemClick: (TrendingData) -> Unit) : RecyclerView.Adapter<ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_data, parent, false)
        return ItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        holder.icon.setImageDrawable(context.getDrawable(item.icon))
        holder.tvTitle.text = item.title
        holder.tvDescription.text = item.description

        holder.itemView.setOnClickListener {
            onItemClick(item)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val icon: AppCompatImageView = itemView.findViewById(R.id.ivLogo)
    val tvTitle: AppCompatTextView = itemView.findViewById(R.id.tvTitle)
    val tvDescription: AppCompatTextView = itemView.findViewById(R.id.tvDescription)
}

