package com.yorkismine.notesappnow

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import org.w3c.dom.Text


class CustomRadioButton : FrameLayout {
    private var title: String = ""
    private var price: String = ""
    private var allTextColor: Int = 0
    private var colorState: ColorStateList? = null
    var isAccessed = true

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomRadioButton)
        title = typedArray.getString(R.styleable.CustomRadioButton_title)!!
        price = typedArray.getString(R.styleable.CustomRadioButton_price)!!
        allTextColor = typedArray.getColor(R.styleable.CustomRadioButton_android_textColor, 0)

        View.inflate(context, R.layout.custom_radio_btn, this)
        this.findViewById<TextView>(R.id.title).apply {
            text = title
            colorState = textColors
        }
        this.findViewById<TextView>(R.id.price).text = price
        this.background = ContextCompat.getDrawable(context, R.drawable.crb_back_default)

        typedArray.recycle()
    }

     fun disableSelection() {
        this.findViewById<ImageView>(R.id.done_iv).visibility = View.GONE
        this.background = ContextCompat.getDrawable(context, R.drawable.crb_back_default)
        this.findViewById<TextView>(R.id.title).setTextColor(colorState)
        this.findViewById<TextView>(R.id.price).setTextColor(colorState)
         isAccessed = false
    }

     fun enableSelection() {
        this.findViewById<ImageView>(R.id.done_iv).visibility = View.VISIBLE
        this.background = ContextCompat.getDrawable(context, R.drawable.crb_back_blue)
        this.findViewById<TextView>(R.id.title).setTextColor(allTextColor)
        this.findViewById<TextView>(R.id.price).setTextColor(allTextColor)
    }

    fun disable() {
        isClickable = false
        this.findViewById<ImageView>(R.id.done_iv).visibility = View.GONE
        this.background = ContextCompat.getDrawable(context, R.drawable.crb_disabled)
        this.findViewById<TextView>(R.id.title).setTextColor(ContextCompat.getColor(context, R.color.disableCRB))
        this.findViewById<TextView>(R.id.price).apply {
            text = "Доступ открыт"
            setTextColor(ContextCompat.getColor(context, R.color.disableCRB))
        }
        isAccessed = false
    }

//    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//        var widthMode = MeasureSpec.getMode(widthMeasureSpec)
//        var heightMode = MeasureSpec.getMode(heightMeasureSpec)
//        var widthSize = MeasureSpec.getSize(widthMeasureSpec)
//        var heightSize = MeasureSpec.getSize(heightMeasureSpec)
//        var width = paddingLeft + paddingRight
//        var height = paddingTop + paddingBottom
//
//        if (widthMode == MeasureSpec.EXACTLY) {
//            width = widthSize
//        }
//
//        if (heightMode == MeasureSpec.EXACTLY) {
//            height = heightSize
//        } else height = 200
//
//        setMeasuredDimension(width, height)
//    }
}
