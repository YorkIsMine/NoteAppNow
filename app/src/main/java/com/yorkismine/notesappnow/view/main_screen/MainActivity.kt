package com.yorkismine.notesappnow.view.main_screen

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.yorkismine.notesappnow.R
import com.yorkismine.notesappnow.view.select_type_screen.SelectTypeActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var sharedPrefs: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedPrefs = getSharedPreferences("main_prefs", Context.MODE_PRIVATE)

        if (sharedPrefs.getInt("PLAN", -1) == 1) {
            c1.visibility = View.VISIBLE
            garbage_iv.visibility = View.GONE
            garbage_tv.visibility = View.GONE
        }
        if (sharedPrefs.getInt("SELF_DEV", -1) == 2) {
            c2.visibility = View.VISIBLE
            garbage_iv.visibility = View.GONE
            garbage_tv.visibility = View.GONE
        }
        if (sharedPrefs.getInt("WISH", -1) == 3) {
            c3.visibility = View.VISIBLE
            garbage_iv.visibility = View.GONE
            garbage_tv.visibility = View.GONE
        }

        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, SelectTypeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivityForResult(intent, 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("OOF", "req code is $requestCode")
        Log.d("OOF", "res code is $resultCode")

        if (resultCode == Activity.RESULT_OK) {
            garbage_iv.visibility = View.GONE
            garbage_tv.visibility = View.GONE

            val res = data?.extras?.getInt("int")
            Log.d("OOF", "$res")

            val editor = sharedPrefs.edit()

            when (res) {
                1 -> {
                    c1.visibility = View.VISIBLE
                    editor.putInt("PLAN", 1)
                }
                2 -> {
                    c2.visibility = View.VISIBLE
                    editor.putInt("SELF_DEV", 2)
                }
                3 -> {
                    c3.visibility = View.VISIBLE
                    editor.putInt("WISH", 3)
                }
            }

            editor.apply()
        }
    }
}
