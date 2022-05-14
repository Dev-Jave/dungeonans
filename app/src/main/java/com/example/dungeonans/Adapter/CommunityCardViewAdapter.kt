package com.example.dungeonans.Adapter

import android.content.Intent
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.dungeonans.DataClass.CommunityData
import com.example.dungeonans.Holder.Holder
import com.example.dungeonans.R


class CommunityCardViewAdapter() : RecyclerView.Adapter<Holder>(), Parcelable {
    var listData = mutableListOf<CommunityData>()
    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_cardview,parent,false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.itemView.findViewById<ConstraintLayout>(R.id.postLayout).setOnClickListener{
            itemClickListener.postClick(it,position)
        }
        val data = listData.get(position)
        holder.setCommunityPostValue(data)
    }

    interface OnItemClickListener {
        fun postClick(v: View, position: Int)
    }
    // (3) 외부에서 클릭 시 이벤트 설정
    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }
    // (4) setItemClickListener로 설정한 함수 실행
    private lateinit var itemClickListener : OnItemClickListener

    constructor(parcel: Parcel) : this() {

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CommunityCardViewAdapter> {
        override fun createFromParcel(parcel: Parcel): CommunityCardViewAdapter {
            return CommunityCardViewAdapter(parcel)
        }

        override fun newArray(size: Int): Array<CommunityCardViewAdapter?> {
            return arrayOfNulls(size)
        }
    }

}