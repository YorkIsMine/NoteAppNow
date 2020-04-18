package com.yorkismine.notesappnow.view.select_type_screen

import android.util.Log

class STPresenter(private val view: STContract.View) : STContract.Presenter {
    override fun f(whichCard: Int) {
        if (view.whichSucceed(whichCard)) {
            view.enableAccessBtn()
            view.handleCard(whichCard)
        }
    }

    override fun performAccess() {
        view.disableAccessBtn()
        view.saveAndExit(view.whichEnabled())
    }
}