package com.astronout.androidtestdot.users.view

import android.os.Bundle
import com.astronout.androidtestdot.R
import com.astronout.androidtestdot.baseview.BaseActivity

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}