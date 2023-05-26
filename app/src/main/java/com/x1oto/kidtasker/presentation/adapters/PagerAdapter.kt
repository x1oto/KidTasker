package com.x1oto.kidtasker.presentation.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.x1oto.kidtasker.presentation.view.SignInFragment
import com.x1oto.kidtasker.presentation.view.SignUpFragment

class PagerAdapter(
    fragmentManager: FragmentManager, lifecycle: Lifecycle, private val tabCount: Int
): FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return tabCount
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> SignInFragment()
            1 -> SignUpFragment()
            else -> SignInFragment()
        }
    }
}
