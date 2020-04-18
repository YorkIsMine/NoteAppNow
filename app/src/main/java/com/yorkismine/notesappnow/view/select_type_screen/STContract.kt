package com.yorkismine.notesappnow.view.select_type_screen

interface STContract {
    interface View {
        fun handleCard(whichCard: Int)
        fun enableAccessBtn()
        fun disableAccessBtn()
        fun whichEnabled(): Int
        fun saveAndExit(numOfCard: Int)
        fun whichSucceed(whichCard: Int): Boolean
    }

    interface Presenter {
        fun f(whichCard: Int)
        fun performAccess()
    }
}