package com.tristar.jjinbang.ui.main

import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.tristar.jjinbang.FavoriteRoomAttribute
import com.tristar.jjinbang.R
import kotlinx.android.synthetic.main.custom_favorite_widget.view.*


/**
 * attributes
 * src: Image source URI
 * name: Room name
 * price: Room price xx/yy (보증금/월세 형식)
 * size: Room size (원룸, 투룸, 1.5룸 등등)
 * option: Provided room options(풀옵션, 침대만 제공 등등)
 * floor_height: Room's floor height(1층, 반지하, 2층 등등)
 */

class FavoriteWidget : ConstraintLayout {
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
        LayoutInflater.from(context).inflate(R.layout.custom_favorite_widget, this, true)
    }

    private fun getAttrs(attrs: AttributeSet?){
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.FavoriteWidget)
        setTypeArray(typedArray)
    }

    private fun getAttrs(attrs: AttributeSet?, defStyleAttr: Int){
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.FavoriteWidget, defStyleAttr, 0)
        setTypeArray(typedArray)
    }

    private fun setTypeArray(typedArray: TypedArray){
        val src = typedArray.getString(R.styleable.FavoriteWidget_src)
        if(src != null)
            setSrc(src)

        val name = typedArray.getString(R.styleable.FavoriteWidget_name)
        if(name != null)
            setName(name)

        val price = typedArray.getString(R.styleable.FavoriteWidget_price)
        if(price != null)
            setPrice(price)

        val size = typedArray.getString(R.styleable.FavoriteWidget_size)
        if(size != null)
            setSize(size)

        val option = typedArray.getString(R.styleable.FavoriteWidget_option)
        if(option != null)
            setOption(option)

        val floorHeight = typedArray.getString(R.styleable.FavoriteWidget_floor_height)
        if(floorHeight != null)
            setFloorHeight(floorHeight)
    }

    fun setAttributes(attrs: FavoriteRoomAttribute){
        setSrc(attrs.imageSrc)
        setName(attrs.roomName)
        setPrice(attrs.price)
        setSize(attrs.roomStructure)
        setOption(attrs.option)
        setFloorHeight(attrs.floorHeight)
    }

    fun setSrc(src: Any) {
        if(src is String){
            val uri: Uri = Uri.parse(src)
            custom_imageView.setImageURI(uri)
        }
        else if(src is Uri)
            custom_imageView.setImageURI(src)
    }

    fun setName(name: String){
        custom_room_name.text= name
    }

    fun setPrice(price: String){
        custom_price.text = price
    }

    fun setSize(size: String){
        custom_room_structure.text = size
    }

    fun setOption(option: String){
        custom_option.text = option
    }

    fun setFloorHeight(floorHeight: String){
        custom_floor_height.text = floorHeight
    }
}