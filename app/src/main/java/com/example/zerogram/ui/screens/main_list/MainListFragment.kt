package com.example.zerogram.ui.screens.main_list

import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.zerogram.R
import com.example.zerogram.database.*
import com.example.zerogram.models.CommonModel
import com.example.zerogram.ui.screens.ContactsFragment
import com.example.zerogram.utilities.APP_ACTIVITY
import com.example.zerogram.utilities.AppValueEventListener
import com.example.zerogram.utilities.replaceFragment
import kotlinx.android.synthetic.main.fragment_main_list.*

class MainListFragment : Fragment(R.layout.fragment_main_list) {

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: MainListAdapter
    private val mRefMainList = REF_DATABASE_ROOT.child(NODE_MAIN_LIST).child(CURRENT_UID)
    private val mRefUsers = REF_DATABASE_ROOT.child(NODE_USERS)
    private val mRefMessages = REF_DATABASE_ROOT.child(NODE_MESSAGES).child(CURRENT_UID)
    private var mListItems = listOf<CommonModel>()

    override fun onResume() {
        super.onResume()
        APP_ACTIVITY.title = "Zerogram"
        APP_ACTIVITY.mAppDrawer.enableDrawer()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        mRecyclerView = main_list_recycle_view
        mAdapter = MainListAdapter()

        mRefMainList.addListenerForSingleValueEvent(AppValueEventListener {
            mListItems = it.children.map { it.getCommonModel() }
            if (mListItems.isEmpty()) {
                textSmb.visibility = View.VISIBLE
                textSmb.setOnClickListener { replaceFragment(ContactsFragment()) }
            }
            else{
                textSmb.visibility = View.GONE
                textSmb.setOnClickListener(null)
            }
            mListItems.forEach { model ->

                mRefUsers.child(model.id).addListenerForSingleValueEvent(AppValueEventListener {
                    val newModel = it.getCommonModel()

                    mRefMessages.child(model.id).limitToLast(1)
                        .addListenerForSingleValueEvent(AppValueEventListener {
                            val tempList = it.children.map { it.getCommonModel() }

                            if (tempList.isEmpty())
                                newModel.lastMessage = "Тут пусто -_-"
                            else
                                when (tempList[0].type) {
                                    "text" -> newModel.lastMessage = tempList[0].text
                                    "file" -> newModel.lastMessage = "Файл"
                                    "image" -> newModel.lastMessage = "Фото"
                                }

                            if (newModel.fullname.isEmpty())
                                newModel.fullname = newModel.phone

                            mAdapter.updateListItems(newModel)
                        })
                })
            }
        })
        mRecyclerView.adapter = mAdapter
    }
}