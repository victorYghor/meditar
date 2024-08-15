package com.victoryghor.meditar

import android.app.Activity
import android.content.Intent
import android.os.Bundle

class StartActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // set the splash screen
        val intent = Intent(this,  MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        this.startActivity(intent)
    }
}