package com.example.perpus

import android.net.wifi.hotspot2.pps.HomeSp
import android.os.Bundle
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.perpus.home.HomeFragment
import com.example.perpus.home.NotificationFragment
import com.example.perpus.home.ProfileFragment
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : FragmentActivity() {

    private lateinit var mPagger: ViewPager2
    val NUM_PAGES = 3
    var name_tab = arrayOf("Home","Notif","Profile")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val pageAdapter = ScreenSlidePagerAdapter(this)
        viewPager.adapter = pageAdapter

        val tabLayoutMediator = TabLayoutMediator(tab, viewPager, true,
            TabLayoutMediator.OnConfigureTabCallback { tab, position ->

            })
        tabLayoutMediator.attach();

        for (i in 0..2) {
            tab.getTabAt(i)!!.setText(name_tab.get(i));
        }
    }

    override fun onBackPressed() {
        if (viewPager.currentItem == 0){
            super.onBackPressed()
        }else{
            viewPager.currentItem = mPagger.currentItem - 1
        }
    }

    private inner class ScreenSlidePagerAdapter (fa: FragmentActivity):FragmentStateAdapter(fa){
        override fun createFragment(position: Int): Fragment {
            if (position == 0){
                return HomeFragment()
            }else if (position == 1){
                return NotificationFragment()
            }else{
                return ProfileFragment()
            }
        }

        override fun getItemCount(): Int= NUM_PAGES
    }
}
