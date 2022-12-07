package com.example.zerogram.ui.screens.settings

import com.example.zerogram.R
import com.example.zerogram.database.USER
import com.example.zerogram.database.setBioToDatabase
import com.example.zerogram.ui.screens.BaseChangeFragment
import com.example.zerogram.utilities.APP_ACTIVITY
import kotlinx.android.synthetic.main.fragment_change_bio.*

class ChangeBioFragment : BaseChangeFragment(R.layout.fragment_change_bio){

    override fun onResume() {
        super.onResume()
        settings_input_bio.setText(USER.bio)
        APP_ACTIVITY.title = "О себе"
    }

    override fun change() {
        super.change()
        val newBio = settings_input_bio.text.toString()

        setBioToDatabase(newBio)
    }
}