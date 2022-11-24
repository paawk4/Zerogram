package com.example.zerogram.ui.message_recycle_view.view_holders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.zerogram.database.CURRENT_UID
import com.example.zerogram.ui.message_recycle_view.views.MessageView
import com.example.zerogram.utilities.AppVoicePlayer
import com.example.zerogram.utilities.asTime
import kotlinx.android.synthetic.main.message_item_voice.view.*

class HolderVoiceMessage(view: View) : RecyclerView.ViewHolder(view), MessageHolder {
    private val mAppVoicePlayer = AppVoicePlayer()

    private val blockUserVoiceMessage: ConstraintLayout = view.block_user_voice_message
    private val chatUserVoiceMessageTime: TextView = view.chat_user_voice_message_time

    private val blockReceivedVoiceMessage: ConstraintLayout = view.block_received_voice_message
    private val chatReceivedVoiceMessageTime: TextView = view.chat_received_voice_message_time

    private val chatReceivedBtnPlay: ImageView = view.chat_received_btn_play
    private val chatReceivedBtnPause: ImageView = view.chat_received_btn_pause

    private val chatUserBtnPlay: ImageView = view.chat_user_btn_play
    private val chatUserBtnPause: ImageView = view.chat_user_btn_pause

    override fun drawMessage(view: MessageView) {
        if (view.from == CURRENT_UID) {
            blockReceivedVoiceMessage.visibility = View.GONE
            blockUserVoiceMessage.visibility = View.VISIBLE
            chatUserVoiceMessageTime.text =
                view.timeStamp.asTime()
        } else {
            blockReceivedVoiceMessage.visibility = View.VISIBLE
            blockUserVoiceMessage.visibility = View.GONE
            chatReceivedVoiceMessageTime.text =
                view.timeStamp.asTime()
        }
    }

    override fun onAttach(view: MessageView) {
        mAppVoicePlayer.init()
        if (view.from == CURRENT_UID) {
            chatUserBtnPlay.setOnClickListener {
                chatUserBtnPlay.visibility = View.GONE
                chatUserBtnPause.visibility = View.VISIBLE
                chatUserBtnPause.setOnClickListener {
                    stop{
                        chatUserBtnPause.setOnClickListener(null)
                        chatUserBtnPlay.visibility = View.VISIBLE
                        chatUserBtnPause.visibility = View.GONE
                    }
                }
                play(view){
                    chatUserBtnPlay.visibility = View.VISIBLE
                    chatUserBtnPause.visibility = View.GONE
                }
            }
        } else {
            chatReceivedBtnPlay.setOnClickListener {
                chatReceivedBtnPlay.visibility = View.GONE
                chatReceivedBtnPause.visibility = View.VISIBLE
                chatReceivedBtnPause.setOnClickListener {
                    stop{
                        chatReceivedBtnPause.setOnClickListener(null)
                        chatReceivedBtnPlay.visibility = View.VISIBLE
                        chatReceivedBtnPause.visibility = View.GONE
                    }
                }
                play(view){
                    chatReceivedBtnPlay.visibility = View.VISIBLE
                    chatReceivedBtnPause.visibility = View.GONE

                }
            }
        }
    }

    private fun play(view: MessageView, function: () -> Unit) {
        mAppVoicePlayer.play(view.id, view.fileUrl){
            function()
        }
    }

    private fun stop(function: () -> Unit){
        mAppVoicePlayer.stop {
            function()
        }
    }

    override fun onDetach() {
        chatUserBtnPlay.setOnClickListener(null)
        chatReceivedBtnPlay.setOnClickListener(null)
        mAppVoicePlayer.release()
    }
}