package com.example.dungeonans.Holder

import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dungeonans.DataClass.PostCommentData
import com.example.dungeonans.R

class PostCommentHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    var commentWriterProfile : ImageView = itemView.findViewById(R.id.commentWriterProfile)
    var commentWriterName : TextView = itemView.findViewById(R.id.commentWriterName)
    var commentWriterNickName : TextView = itemView.findViewById(R.id.commentWriterNickName)
    var commmentWriteTime : TextView = itemView.findViewById(R.id.commentWriteTime)
    var commentBody : TextView = itemView.findViewById(R.id.commentBody)

    fun setValue(listData : PostCommentData) {
        commentWriterProfile.setBackgroundResource(listData.commentWriteProfile)
        commentWriterName.text = listData.commentWriterName
        commentWriterNickName.text = listData.commentWriterNickname
        commmentWriteTime.text = listData.commentWriteTime
        commentBody.text = listData.commentBody
    }
}