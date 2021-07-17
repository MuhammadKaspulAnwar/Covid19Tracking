package com.example.basickotlin

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.basickotlin.dashboard.DashboardActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP)
        {
            window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        }

        Handler(Looper.myLooper()!!).postDelayed({
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }, 5000)


    }

    
}