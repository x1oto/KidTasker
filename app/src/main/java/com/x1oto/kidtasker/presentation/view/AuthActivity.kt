package com.x1oto.kidtasker.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.x1oto.kidtasker.presentation.adapters.PagerAdapter
import com.x1oto.kidtasker.R

class AuthActivity : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager)

        setupViewPager(viewPager, tabLayout)
    }

    private fun setupViewPager(viewPager: ViewPager2, tabLayout: TabLayout){

        tabLayout.addTab(tabLayout.newTab().setText("Sign In"))
        tabLayout.addTab(tabLayout.newTab().setText("Sign Up"))

        val adapter = PagerAdapter(supportFragmentManager, lifecycle, tabLayout.tabCount)
        viewPager.adapter = adapter

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tabLayout.selectTab(tabLayout.getTabAt(position))
            }
        })

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (viewPager.currentItem != tab!!.position) {
                    viewPager.currentItem = tab.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}

        })
    }

}
