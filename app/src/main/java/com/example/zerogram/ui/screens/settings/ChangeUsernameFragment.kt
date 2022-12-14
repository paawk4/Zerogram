package com.example.zerogram.ui.screens.settings

import com.example.zerogram.R
import com.example.zerogram.database.*
import com.example.zerogram.ui.screens.BaseChangeFragment
import com.example.zerogram.utilities.APP_ACTIVITY
import com.example.zerogram.utilities.AppValueEventListener
import com.example.zerogram.utilities.showToast
import kotlinx.android.synthetic.main.fragment_change_username.*
import java.util.*

class ChangeUsernameFragment : BaseChangeFragment(R.layout.fragment_change_username) {

    private lateinit var mNewUsername: String

    override fun onResume() {
        super.onResume()
        settings_input_username.setText((USER.username))
        APP_ACTIVITY.title = "Имя пользователя"
    }

    override fun change() {
        mNewUsername = settings_input_username.text.toString().lowercase(Locale.getDefault())
        if (mNewUsername.isEmpty()) {
            showToast("Поле пустое")
        } else {
            REF_DATABASE_ROOT.child(NODE_USERNAMES)
                .addListenerForSingleValueEvent(AppValueEventListener {
                    if (it.hasChild(mNewUsername)) {
                        showToast("Такой пользователь уже существует")
                    } else {
                        changeUserName()
                    }

                })
        }
    }

    private fun changeUserName() {
        REF_DATABASE_ROOT.child(NODE_USERNAMES).child(mNewUsername).setValue(CURRENT_UID)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    updateCurrentUsername(mNewUsername)
                }
            }
    }
}