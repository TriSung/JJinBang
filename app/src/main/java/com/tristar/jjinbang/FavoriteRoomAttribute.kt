package com.tristar.jjinbang

import android.graphics.drawable.Drawable
import android.net.Uri
import java.util.*
import kotlin.math.floor

/**
 * attributes
 * src: Image source URI
 * name: Room name
 * price: Room price xx/yy (보증금/월세 형식)
 * size: Room size (원룸, 투룸, 1.5룸 등등)
 * option: Provided room options(풀옵션, 침대만 제공 등등)
 * floor_height: Room's floor height(1층, 반지하, 2층 등등)
 */

class FavoriteRoomAttribute() {
    lateinit var src: Any
    lateinit var name: String
    lateinit var price: String
    lateinit var size: String
    lateinit var option: String
    lateinit var floorHeight: String

    init{ }

    constructor(src: Drawable, name: String, price: String, size: String, option: String, floorHeight: String) : this(){
        this.src = src
        this.name = name
        this.price = price
        this.size = size
        this.option = option
        this.floorHeight = floorHeight
    }
    constructor(src: String, name: String, price: String, size: String, option: String, floorHeight: String) : this(){
        this.src = src
        this.name = name
        this.price = price
        this.size = size
        this.option = option
        this.floorHeight = floorHeight
    }
    constructor(src: Uri, name: String, price: String, size: String, option: String, floorHeight: String) : this(){
        this.src = src
        this.name = name
        this.price = price
        this.size = size
        this.option = option
        this.floorHeight = floorHeight
    }

}