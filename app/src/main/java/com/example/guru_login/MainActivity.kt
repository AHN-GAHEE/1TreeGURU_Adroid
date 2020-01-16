package com.example.guru_login

import FragmentBookmark
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val fragmentManager = supportFragmentManager

    private val fragmentHome = FragmentHome()
    private val fragmentSearch = FragmentSearch()
    private val fragmentAdd = FragmentAdd()
    private val fragmentBookmark = FragmentBookmark()
    private val fragmentMypage = FragmentMypage()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout, fragmentHome).commitAllowingStateLoss()

        val bottomNavigationView = navigationView
        bottomNavigationView.setOnNavigationItemSelectedListener(ItemSelectedListener())
    }

    internal inner class ItemSelectedListener :
        BottomNavigationView.OnNavigationItemSelectedListener {
        override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
            val transaction = fragmentManager.beginTransaction()

            when (menuItem.getItemId()) {
                R.id.btn_Home -> transaction.replace(
                    R.id.frameLayout,
                    fragmentHome
                ).commitAllowingStateLoss()

                R.id.btn_Search -> transaction.replace(
                    R.id.frameLayout,
                    fragmentSearch
                ).commitAllowingStateLoss()

                R.id.btn_Add -> transaction.replace(
                    R.id.frameLayout,
                    fragmentAdd
                ).commitAllowingStateLoss()

                R.id.btn_Bookmark -> transaction.replace(
                    R.id.frameLayout,
                    fragmentBookmark
                ).commitAllowingStateLoss()

                R.id.btn_Mypage -> transaction.replace(
                    R.id.frameLayout,
                    fragmentMypage
                ).commitAllowingStateLoss()
            }
            return true
        }
    }
}