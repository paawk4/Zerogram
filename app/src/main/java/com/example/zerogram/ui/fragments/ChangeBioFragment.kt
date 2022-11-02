package com.example.zerogram.ui.fragments

import com.example.zerogram.R
import com.example.zerogram.utilities.*
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
        REF_DATABASE_ROOT.child(NODE_USERS).child(CURRENT_UID).child(CHILD_BIO).setValue(newBio).addOnCompleteListener {
            if (it.isSuccessful){
                showToast(getString(R.string.toast_data_update))
                USER.bio = newBio
                fragmentManager?.popBackStack()
                hideKeyboard()
            }
        }
    }
}