package com.example.zerogram.ui

import android.os.Binder
import android.os.Bundle
import android.renderscript.ScriptGroup.Binding
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.zerogram.R
import com.example.zerogram.databinding.ActivityMainBinding
import com.example.zerogram.databinding.FragmentChatsBinding

class ChatsFragment : Fragment() {
    private lateinit var mBinding: FragmentChatsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentChatsBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return mBinding.root
    }

    override fun onResume() {
        super.onResume()
    }

}