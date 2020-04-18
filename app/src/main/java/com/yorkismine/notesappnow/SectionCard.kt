package com.yorkismine.notesappnow

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView

class SectionCard : FrameLayout {
    private var icon: Drawable? = null
    private var title: String = ""
    private var content: String = ""

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.SectionCard)
        title = typedArray.getString(R.styleable.SectionCard_title_sc)!!
        content = typedArray.getString(R.styleable.SectionCard_content_sc)!!
        icon = typedArray.getDrawable(R.styleable.SectionCard_android_src)

        View.inflate(context, R.layout.section_card, this)

        this.findViewById<TextView>(R.id.title_sc).text = title
        this.findViewById<TextView>(R.id.content_sc).text = content
        this.findViewById<ImageView>(R.id.icon_sc).setImageDrawable(icon)

        typedArray.recycle()
    }





}