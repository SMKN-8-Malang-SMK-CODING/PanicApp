package com.panic.panicapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity


class splashActivity : AppCompatActivity() {
    private var mDelayHandler : Handler? = null

    private val SPLASH_DELAY : Long = 500

    internal val mRunnable: Runnable = Runnable{
        if(!isFinishing){
            val intent = Intent(applicationContext, MainActivity::class.java)

            startActivity(intent)

            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.splash_layout)

        mDelayHandler = Handler()

        mDelayHandler!!.postDelayed(mRunnable, SPLASH_DELAY)
    }

    override fun onDestroy() {

        if(mDelayHandler != null){
            mDelayHandler!!.removeCallbacks(mRunnable)
        }
        super.onDestroy()
    }

}