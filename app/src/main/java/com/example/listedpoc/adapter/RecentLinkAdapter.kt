package com.example.listedpoc.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.listedpoc.R
import com.example.listedpoc.helper.loadImage
import com.example.listedpoc.model.LinkModel
import com.example.listedpoc.model.RecentLink
import java.text.SimpleDateFormat
import java.util.Locale

class RecentLinkAdapter(
    private val items: List<RecentLink>?,
    private val context: Context,
    private val onItemClick: (RecentLink?) -> Unit) : RecyclerView.Adapter<ItemRecentLinkCardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemRecentLinkCardViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_link_card, parent, false)
        return ItemRecentLinkCardViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ItemRecentLinkCardViewHolder, position: Int) {
        val item = items?.get(position)
        holder.tvLink.text = item?.webLink
        holder.tvClickCount.text = item?.totalClicks.toString()
        holder.tvLinkname.text = item?.title
        holder.tvDate.text = formatDate(item?.createdAt)
        holder.icon.loadImage(item?.thumbnail ?: item?.originalImage)

        holder.clLink.setOnClickListener {
            onItemClick(item)
        }
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    fun formatDate(dateString: String?): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        if (dateString != null){
            val date = inputFormat.parse(dateString)
            return outputFormat.format(date)
        }else return ""
    }
}

class ItemRecentLinkCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val icon: AppCompatImageView = itemView.findViewById(R.id.icon)
    val clLink: ConstraintLayout = itemView.findViewById(R.id.clLink)
    val tvLink: TextView = itemView.findViewById(R.id.tvLink)
    val tvClickCount: TextView = itemView.findViewById(R.id.tvClickCount)
    val tvLinkname: TextView = itemView.findViewById(R.id.tvLinkname)
    val tvDate: TextView = itemView.findViewById(R.id.tvDate)
}