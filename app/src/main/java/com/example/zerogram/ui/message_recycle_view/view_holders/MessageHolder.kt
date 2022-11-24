package com.example.zerogram.ui.message_recycle_view.view_holders

import com.example.zerogram.ui.message_recycle_view.views.MessageView

interface MessageHolder {
    fun drawMessage(view: MessageView)
    fun onAttach(view: MessageView)
    fun onDetach()
}