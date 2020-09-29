package com.astronout.androidtestdot.widget

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import com.astronout.androidtestdot.R
import kotlinx.android.synthetic.main.layout_no_internet_connection.*

class NoInternetConnectionDialog(context : Context, val onActionOK : () -> Unit) : Dialog(context){

    init {
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)

        setContentView(R.layout.layout_no_internet_connection)

        dialog_retry.setOnClickListener {
            dismiss()
            onActionOK()
        }
    }
}