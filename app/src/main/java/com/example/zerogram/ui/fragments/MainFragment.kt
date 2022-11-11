package com.example.zerogram.ui.fragments

import androidx.fragment.app.Fragment
import com.example.zerogram.R
import com.example.zerogram.utilities.APP_ACTIVITY

class MainFragment : Fragment(R.layout.fragment_chats) {
    override fun onResume() {
        super.onResume()
        APP_ACTIVITY.title = "Zerogram"
        APP_ACTIVITY.mAppDrawer.enableDrawer()
    }
}