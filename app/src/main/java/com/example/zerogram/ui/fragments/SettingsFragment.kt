package com.example.zerogram.ui.fragments

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.example.zerogram.MainActivity
import com.example.zerogram.R
import com.example.zerogram.activities.RegisterActivity
import com.example.zerogram.utilities.AUTH
import com.example.zerogram.utilities.replaceActivity

class SettingsFragment : BaseFragment(R.layout.fragment_settings) {

    override fun onResume() {
        super.onResume()
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.settings_action_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.settings_menu_exit -> {
                AUTH.signOut()
                (activity as MainActivity).replaceActivity(RegisterActivity())
            }
        }
        return true
    }
}