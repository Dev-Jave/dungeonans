package com.example.dungeonans.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dungeonans.DataClass.PostCommentData
import com.example.dungeonans.Holder.PostCommentHolder
import com.example.dungeonans.R
import org.w3c.dom.Text

class PostCommentCardViewAdapter : RecyclerView.Adapter<PostCommentHolder>(){
    var listData = mutableListOf<PostCommentData>()

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostCommentHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_comment_cardview,parent,false)
        return PostCommentHolder(view)
    }

    override fun onBindViewHolder(holder: PostCommentHolder, position: Int) {

        holder.itemView.findViewById<ImageView>(R.id.replyCommentBtn).setOnClickListener{
            itemClickListener.commentClick(it,position)
        }
        holder.itemView.findViewById<ImageView>(R.id.commentThumbsUp).setOnClickListener{
            itemClickListener.likeClick(it,position)
            holder.itemView.findViewById<TextView>(R.id.likeCount).text = (Integer.parseInt(holder.itemView.findViewById<TextView>(R.id.likeCount).text.toString()) + 1).toString()
        }

        val data = listData[position]
        holder.setValue(data)
    }

    // (2) 리스너 인터페이스
    interface OnItemClickListener {
        fun commentClick(v: View, position: Int)
        fun likeClick(v: View,position: Int)
    }
    // (3) 외부에서 클릭 시 이벤트 설정
    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }
    // (4) setItemClickListener로 설정한 함수 실행
    private lateinit var itemClickListener : OnItemClickListener
}