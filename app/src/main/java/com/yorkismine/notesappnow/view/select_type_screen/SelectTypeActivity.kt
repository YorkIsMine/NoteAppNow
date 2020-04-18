package com.yorkismine.notesappnow.view.select_type_screen

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import com.yorkismine.notesappnow.R
import kotlinx.android.synthetic.main.activity_select_type.*
import java.lang.IllegalArgumentException

class SelectTypeActivity : AppCompatActivity(), STContract.View {
    companion object {
        private const val PLAN_CARD = 1
        private const val SELF_DEV_CARD = 2
        private const val WISH_CARD = 3
    }

    private lateinit var sharedPrefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_type)
        val presenter: STContract.Presenter = STPresenter(this)
        sharedPrefs = getSharedPreferences("st_prefs", Context.MODE_PRIVATE)

        if (sharedPrefs.getInt("PLAN", -1) == 1) plan_card.disable()
        if (sharedPrefs.getInt("SELF_DEV", -1) == 2) self_develop_card.disable()
        if (sharedPrefs.getInt("WISH", -1) == 3) wish_card.disable()

        access_btn.setOnClickListener {
            presenter.performAccess()
        }

        plan_card.setOnClickListener {
            presenter.f(PLAN_CARD) // selectCard()
        }

        self_develop_card.setOnClickListener {
            presenter.f(SELF_DEV_CARD)
        }

        wish_card.setOnClickListener {
            presenter.f(WISH_CARD)
        }
    }

    override fun handleCard(whichCard: Int) {
        when (whichCard) {
            PLAN_CARD -> {
                plan_card.enableSelection()
                wish_card.disableSelection()
                self_develop_card.disableSelection()

            }
            SELF_DEV_CARD -> {
                self_develop_card.enableSelection()
                wish_card.disableSelection()
                plan_card.disableSelection()

            }
            WISH_CARD -> {
                wish_card.enableSelection()
                plan_card.disableSelection()
                self_develop_card.disableSelection()

            }
        }
    }

    override fun enableAccessBtn() {
        access_btn.apply {
            background = ContextCompat.getDrawable(
                this@SelectTypeActivity,
                R.drawable.access_btn_back_enabled
            )
            isClickable = true
        }
    }

    override fun disableAccessBtn() {
        access_btn.apply {
            background = ContextCompat.getDrawable(
                this@SelectTypeActivity,
                R.drawable.access_btn_back_disabled
            )
            isClickable = false
        }
    }

    override fun whichEnabled(): Int {
        if (plan_card.isAccessed) {
            plan_card.disable()
            return 1
        }
        if (self_develop_card.isAccessed) {
            self_develop_card.disable()
            return 2
        }
        if (wish_card.isAccessed) {
            wish_card.disable()
            return 3
        }

        return -1
    }

    override fun saveAndExit(numOfCard: Int) {
        val editor = sharedPrefs.edit()
        when (numOfCard) {
            1 -> editor.putInt("PLAN", 1)
            2 -> editor.putInt("SELF_DEV", 2)
            3 -> editor.putInt("WISH", 3)
        }
        editor.apply()


        val returnIntent = Intent()
        returnIntent.putExtra("int", numOfCard)
        Log.d("OOF", returnIntent.getIntExtra("int", 0).toString() + "ada")
        setResult(RESULT_OK, returnIntent)
        finish()
    }

    override fun whichSucceed(whichCard: Int): Boolean {
        when (whichCard) {
            1 -> return plan_card.isAccessed
            2 -> return self_develop_card.isAccessed
            3 -> return wish_card.isAccessed
        }

        throw IllegalArgumentException("This num of card don't exist")
    }


}
