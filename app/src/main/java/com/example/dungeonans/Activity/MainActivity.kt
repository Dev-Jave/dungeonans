package com.example.dungeonans.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.example.dungeonans.Adapter.ViewPagerAdapter
import com.example.dungeonans.R
import com.example.dungeonans.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationBarView
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startbinding()
        connectToolbar()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu,menu)
        return true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var topbar = findViewById<ConstraintLayout>(R.id.mainActivityPostingLayout)
        when(item.itemId){
            R.id.nav_Community -> {
                binding.pager.currentItem = 0
                topbar.visibility = View.VISIBLE
                return true
            }
            R.id.nav_Ask -> {
                binding.pager.currentItem = 1
                topbar.visibility = View.VISIBLE
                return true
            }
            R.id.nav_Blog -> {
                binding.pager.currentItem = 2
                topbar.visibility = View.VISIBLE
                return true
            }
            R.id.nav_MyProfile -> {
                binding.pager.currentItem = 3
                topbar.visibility = View.GONE
                return true
            }
            else -> {
                return false
            }
        }
    }

    // 상단 옵션 클릭 이벤트
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_search -> {
                true
            }
            R.id.action_share -> {
                var intent = Intent(this,NoticeActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun startbinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.pager.adapter = ViewPagerAdapter(this)
        binding.pager.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    var topbar = findViewById<ConstraintLayout>(R.id.mainActivityPostingLayout)
                    binding.bottomNavigationView.menu.getItem(position).isChecked = true
                    if (position == 3) {
                        topbar.visibility = View.GONE
                    } else {
                        topbar.visibility = View.VISIBLE
                    }
                }
            }
        )
        binding.bottomNavigationView.setOnItemSelectedListener(this)
    }

    fun connectToolbar() {
        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        var actionBar = supportActionBar
        actionBar!!.setDisplayShowCustomEnabled(true)
        actionBar!!.title = null
    }

    fun showProfile() {
        var intent = Intent(this,UserProfileActivity::class.java)
        startActivity(intent)
    }
}