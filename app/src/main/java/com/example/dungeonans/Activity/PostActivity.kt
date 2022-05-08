package com.example.dungeonans.Activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dungeonans.Adapter.BlogCardViewAdapter
import com.example.dungeonans.Adapter.PostCommentCardViewAdapter
import com.example.dungeonans.DataClass.PostCommentData
import com.example.dungeonans.R
import retrofit2.http.Body

class PostActivity : AppCompatActivity() {
    var commentdata : MutableList<PostCommentData> = mutableListOf()
    var answerData : MutableList<PostCommentData> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ask_post_fragment)

        var commentEditText : EditText = findViewById(R.id.commentEditText)
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
        }

        renderCommentUi(commentEditText)
        renderAnswerUi(commentEditText)
    }

    private fun renderCommentUi(commentEditText : EditText) {
        var recyclerView : RecyclerView = findViewById(R.id.postCommentRecyclerView)
        var data : MutableList<PostCommentData> = setCommentData()
        var adapter = PostCommentCardViewAdapter()
        adapter.setItemClickListener(object : PostCommentCardViewAdapter.OnItemClickListener {
            override fun onClick(v: View, position: Int) {
                Log.d("tag",adapter.itemCount.toString())
                commentEditText.requestFocus()
                commentEditText.hint = "대댓글을 작성해보세요"
                var manager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                manager.showSoftInput(commentEditText, InputMethodManager.SHOW_IMPLICIT)
            }
        })

        adapter.listData = data
        recyclerView.adapter = adapter
        LinearLayoutManager(this).also { recyclerView.layoutManager = it }
    }

    private fun renderAnswerUi(commentEditText : EditText) {
        var recyclerView : RecyclerView = findViewById(R.id.postAnswerRecyclerView)
        var data : MutableList<PostCommentData> = setAnswerData()
        var adapter = PostCommentCardViewAdapter()
        adapter.setItemClickListener(object : PostCommentCardViewAdapter.OnItemClickListener {
            override fun onClick(v: View, position: Int) {
                Log.d("tag",adapter.itemCount.toString())
                commentEditText.requestFocus()
                commentEditText.hint = "답변에 대한 댓글을 작성해보세요"
                var manager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                manager.showSoftInput(commentEditText, InputMethodManager.SHOW_IMPLICIT)
            }
        })
        adapter.listData = data
        recyclerView.adapter = adapter
        LinearLayoutManager(this).also { recyclerView.layoutManager = it }
    }


    private fun putComment(body: String, commentEditText : EditText) {
        var recyclerView : RecyclerView = findViewById(R.id.postAnswerRecyclerView)
        var data : MutableList<PostCommentData> = putCommentValue(body)
        var adapter = PostCommentCardViewAdapter()
        adapter.setItemClickListener(object : PostCommentCardViewAdapter.OnItemClickListener {
            override fun onClick(v: View, position: Int) {
                Log.d("tag",adapter.itemCount.toString())
                commentEditText.hint = "답변에 대한 댓글을 작성해보세요"
                commentEditText.requestFocus()
                var manager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                manager.showSoftInput(commentEditText, InputMethodManager.SHOW_IMPLICIT)
            }
        })
        adapter.listData = data
        recyclerView.adapter = adapter
        adapter.notifyItemInserted(0)
    }

    private fun setCommentData() : MutableList<PostCommentData> {
        for (index in 0 until 6) {
            var commentWriteProfile = R.drawable.profile_base_icon
            var commentWriterName = "${index}번째 작성자"
            var commentWriterNickname = "(@yongkingg)"
            var commentWriteTime = "03/21 12:45"
            var commentBody = "안녕하세요 안녕하세요 안녕하세요 안녕하세요 안녕하세요"
            var listData = PostCommentData(commentWriteProfile,commentWriterName,commentWriterNickname,commentWriteTime,commentBody)
            commentdata.add(listData)
        }
        return commentdata
    }

    private fun setAnswerData() : MutableList<PostCommentData> {
        for (index in 0 until 6) {
            var commentWriteProfile = R.drawable.profile_base_icon
            var commentWriterName = "${index}번째 작성자"
            var commentWriterNickname = "(@yongkingg)"
            var commentWriteTime = "03/21 12:45"
            var commentBody = "안녕하세요 안녕하세요 안녕하세요 안녕하세요 안녕하세요"
            var listData = PostCommentData(commentWriteProfile,commentWriterName,commentWriterNickname,commentWriteTime,commentBody)
            answerData.add(listData)
        }
        return answerData
    }

    private fun putCommentValue(body: String) : MutableList<PostCommentData> {
        var commentWriteProfile = R.drawable.profile_base_icon
        var commentWriterName = "번째 작성자"
        var commentWriterNickname = "(@yongkingg)"
        var commentWriteTime = "03/21 12:45"
        var commentBody = body
        var listData = PostCommentData(commentWriteProfile,commentWriterName,commentWriterNickname,commentWriteTime,commentBody)
        answerData.add(listData)
        return answerData
    }
}