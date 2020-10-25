package com.tristar.jjinbang.ui.main

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout

class ItemWidget : ConstraintLayout {
    constructor(context: Context): super(context, null){
        initLayout()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs, 0){
        initLayout()
        if(attrs != null){
            getAttrs(attrs)
        }
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int):
            super(context, attrs, defStyleAttr){
        initLayout()
        if(attrs != null){
            getAttrs(attrs, defStyleAttr)
        }
    }

    private fun initLayout(){

    }

    private fun getAttrs(attrs: AttributeSet?){

    }

    private fun getAttrs(attrs: AttributeSet?, defStyleAttr: Int){

    }
}