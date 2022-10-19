package com.example.zerogram.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.zerogram.R
import com.example.zerogram.databinding.ActivityRegisterBinding
import com.example.zerogram.ui.fragments.EnterPhoneNumberFragment
import com.example.zerogram.utilities.replaceFragment

class RegisterActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityRegisterBinding
    private lateinit var mToolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    override fun onStart() {
        super.onStart()
        mToolbar = mBinding.registerToolbar
        setSupportActionBar(mToolbar)
        title = getString(R.string.register_title_your_phone)
        replaceFragment(EnterPhoneNumberFragment(),false)
    }
}