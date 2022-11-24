package com.example.zerogram.ui.screens

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.zerogram.R
import com.example.zerogram.utilities.APP_ACTIVITY
import com.example.zerogram.utilities.hideKeyboard

open class BaseChangeFragment (layout: Int): Fragment(layout) {

    override fun onStart() {
        super.onStart()
        setHasOptionsMenu(true)
        (APP_ACTIVITY).mAppDrawer.disableDrawer()
        hideKeyboard()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        (APP_ACTIVITY).menuInflater.inflate(R.menu.settings_menu_confirm, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings_confirm_change -> change()
        }
        return true
    }

    open fun change() {

    }
}