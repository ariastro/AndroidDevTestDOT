package com.astronout.androidtestdot.baseview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.astronout.androidtestdot.widget.AppProgressDialog
import com.google.android.material.button.MaterialButton

abstract class BaseActivity : AppCompatActivity() {

    lateinit var progress : AppProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupProgress()
    }

    protected fun setupProgress() {
        progress = AppProgressDialog(this)
        progress.setCancelable(false)
        progress.setCanceledOnTouchOutside(false)
    }

}