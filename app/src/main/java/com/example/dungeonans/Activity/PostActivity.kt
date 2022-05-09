package com.example.dungeonans.Activity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dungeonans.Adapter.PostCommentCardViewAdapter
import com.example.dungeonans.DataClass.PostCommentData
import com.example.dungeonans.R



// 답변은 답변만 보여주고, 답변의 점점점을 누르면 답변 밑에 달린 모든 댓글들 다 볼 수 있게 처리,,

class PostActivity : AppCompatActivity() {
    var commentData : MutableList<PostCommentData> = mutableListOf()
    private var doubleBackToExitPressedOnce = false

    // 리사이클러뷰 포지션 초기화
    var setRecyclerView = 0
    var commentPosition = 0

    // 댓글, 답변 개수 초기화
    var commentItemCount = 0
    lateinit var recyclerView : RecyclerView
    lateinit var commentEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ask_post_fragment)

        recyclerView = findViewById(R.id.postCommentRecyclerView)

        commentEditText = findViewById(R.id.commentEditText)
        commentEditText.setOnClickListener{
            commentEditText.requestFocus()
        }

        var writeCommentBtn : ImageButton = findViewById(R.id.writeCommentBtn)
        writeCommentBtn.setOnClickListener {
            var bodyValue = commentEditText.text.toString()
            putComment(bodyValue,commentEditText)
            commentEditText.text.clear()
            commentEditText.clearFocus()
            var manager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            manager.hideSoftInputFromWindow(commentEditText.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            commentPosition = recyclerView.adapter!!.itemCount
            commentEditText.hint = "댓글을 입력하세요"
        }

        renderCommentUi(commentEditText)
    }

    private fun renderCommentUi(commentEditText : EditText) {
        var data : MutableList<PostCommentData> = setCommentData()
        var adapter = PostCommentCardViewAdapter()
        adapter.setItemClickListener(object : PostCommentCardViewAdapter.OnItemClickListener {
            override fun onClick(v: View, position: Int) {
                commentPosition = position
                setRecyclerView = 0
                commentEditText.requestFocus()
                commentEditText.hint = "대댓글을 작성해보세요"
                var manager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                manager.showSoftInput(commentEditText, InputMethodManager.SHOW_IMPLICIT)
            }
        })
        adapter.listData = data
        recyclerView.adapter = adapter
        LinearLayoutManager(this).also { recyclerView.layoutManager = it }
        commentPosition = recyclerView.adapter!!.itemCount
        commentItemCount = recyclerView.adapter!!.itemCount
    }


    private fun putComment(body: String, commentEditText : EditText) {
        var data : MutableList<PostCommentData> = putCommentValue(body,commentPosition)
        var adapter = PostCommentCardViewAdapter()

        adapter.setItemClickListener(object : PostCommentCardViewAdapter.OnItemClickListener {
            override fun onClick(v: View, position: Int) {
                var tempPosition = position
                commentPosition = tempPosition
                commentEditText.hint = "답변에 대한 댓글을 작성해보세요"
                // 키보드 관련
                commentEditText.requestFocus()
                var manager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                manager.showSoftInput(commentEditText, InputMethodManager.SHOW_IMPLICIT)
            }
        })
        adapter.listData = data
        adapter.notifyItemInserted(commentPosition)
        recyclerView.adapter = adapter

    }

    private fun setCommentData() : MutableList<PostCommentData> {
        for (index in 0 until 6) {
            var commentWriteProfile = R.drawable.profile_base_icon
            var commentWriterName = "${index}번째 작성자"
            var commentWriterNickname = "(@yongkingg)"
            var commentWriteTime = "03/21 12:45"
            var commentBody = "안녕하세요 안녕하세요 안녕하세요 안녕하세요 안녕하세요"
            var listData = PostCommentData(commentWriteProfile,commentWriterName,commentWriterNickname,commentWriteTime,commentBody)
            commentData.add(listData)
        }
        return commentData
    }

    private fun putCommentValue(body: String, position : Int) : MutableList<PostCommentData> {
        var commentWriteProfile = R.drawable.profile_base_icon
        var commentWriterName = "번째 작성자"
        var commentWriterNickname = "(@yongkingg)"
        var commentWriteTime = "03/21 12:45"
        var commentBody = body
        var listData = PostCommentData(commentWriteProfile,commentWriterName,commentWriterNickname,commentWriteTime,commentBody)

        try {
            commentData.add(position+1,listData)
        } catch (e : IndexOutOfBoundsException) {
            commentData.add(position,listData)
        }
        return commentData
    }

    // 뒤로가기 버튼 초기화
    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }
        this.doubleBackToExitPressedOnce = true
        commentEditText.hint = "댓글을 입력하세요"
        commentEditText.clearFocus()
        commentPosition = recyclerView.adapter!!.itemCount
        var manager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        manager.hideSoftInputFromWindow(commentEditText.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        Handler(Looper.getMainLooper()).postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
    }
}