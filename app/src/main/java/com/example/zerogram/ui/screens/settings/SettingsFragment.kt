package com.example.zerogram.ui.screens.settings

import android.content.Intent
import android.net.Uri
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.zerogram.R
import com.example.zerogram.database.*
import com.example.zerogram.ui.screens.BaseFragment
import com.example.zerogram.utilities.*
import com.github.dhaval2404.imagepicker.ImagePicker
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : BaseFragment(R.layout.fragment_settings) {

    override fun onResume() {
        super.onResume()
        setHasOptionsMenu(true)
        initFields()
        APP_ACTIVITY.title = "Настройки"
    }

    private fun initFields() {
        if (USER.bio != "") {
            settings_bio.text = USER.bio
        }

        settings_full_name.text = USER.fullname
        settings_phone_number.text = USER.phone
        settings_status.text = USER.state
        settings_username.text = USER.username
        settings_btn_change_username.setOnClickListener { replaceFragment(ChangeUsernameFragment()) }
        settings_btn_change_bio.setOnClickListener { replaceFragment(ChangeBioFragment()) }
        settings_change_photo.setOnClickListener { changePhotoUser() }
        settings_user_photo.downloadAndSetImage(USER.photoUrl)
    }

    private fun changePhotoUser() {
        ImagePicker.with(this)
            .crop(1F,1F)
            .compress(1024)
            .maxResultSize(600, 600)
            .start()
//        CropImage.activity()
//            .setAspectRatio(1, 1)
//            .setRequestedSize(250, 250)
//            .setCropShape(CropImageView.CropShape.OVAL)
//            .start(APP_ACTIVITY, this)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.settings_action_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings_menu_exit -> {
                AppStates.updateState(AppStates.OFFLINE)
                AUTH.signOut()
                restartActivity()
            }
            R.id.settings_menu_change_name -> replaceFragment(ChangeNameFragment())
        }
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ImagePicker.REQUEST_CODE
            && resultCode == AppCompatActivity.RESULT_OK && data != null){
            val uri: Uri = data.data!!
            val path = REF_STORAGE_ROOT.child(FOLDER_PROFILE_IMAGE)
                .child(CURRENT_UID)

            putFileToStorage(uri, path) {
                getUrlFromStorage(path) {
                    putUrlToDatabase(it) {
                        settings_user_photo.downloadAndSetImage(it)
                        showToast(getString(R.string.toast_data_update))
                        USER.photoUrl = it
                        APP_ACTIVITY.mAppDrawer.updateHeader()
                    }
                }
            }
        }

//        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE
//            && resultCode == AppCompatActivity.RESULT_OK && data != null
//        ) {
//            val uri = CropImage.getActivityResult(data).uri
//            val path = REF_STORAGE_ROOT.child(FOLDER_PROFILE_IMAGE)
//                .child(CURRENT_UID)
//
//            putFileToStorage(uri, path) {
//                getUrlFromStorage(path) {
//                    putUrlToDatabase(it) {
//                        settings_user_photo.downloadAndSetImage(it)
//                        showToast(getString(R.string.toast_data_update))
//                        USER.photoUrl = it
//                        APP_ACTIVITY.mAppDrawer.updateHeader()
//                    }
//                }
//            }
//        }
    }
}