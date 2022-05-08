package com.example.dungeonans.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dungeonans.DataClass.CommunityData
import com.example.dungeonans.Holder.Holder
import com.example.dungeonans.R


class CommunityCardViewAdapter : RecyclerView.Adapter<Holder>() {
    var listData = mutableListOf<CommunityData>()
    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_cardview,parent,false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val data = listData.get(position)
        holder.setCommunityPostValue(data)
    }
}