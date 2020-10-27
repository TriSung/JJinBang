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

class FavoriteRoomAttribute() : RoomAttributes() {
    companion object{
        const val imageSrcHeader : String = "IMAGESRC"
        const val roomNameHeader : String = "ROOMNAME"
        const val priceHeader : String = "PRICE"
        const val roomStructureHeader: String = "ROOMSTRUCTURE"
        const val optionHeader : String = "OPTION"
        const val floorHeightHeader : String = "FLOORHEIGHT"

        const val delimiter: String = "_"
    }
    init{ }

    constructor(attrs: Set<String>): this(){
        parse(attrs)
    }

    constructor(src: Drawable, name: String, price: String, roomStructure: String, option: String, floorHeight: String) : this(){
        this.imageSrc = src
        this.roomName = name
        this.price = price
        this.roomStructure = roomStructure
        this.option = option
        this.floorHeight = floorHeight
    }
    constructor(src: String, name: String, price: String, roomStructure: String, option: String, floorHeight: String) : this(){
        this.imageSrc = src
        this.roomName = name
        this.price = price
        this.roomStructure = roomStructure
        this.option = option
        this.floorHeight = floorHeight
    }
    constructor(src: Uri, name: String, price: String, roomStructure: String, option: String, floorHeight: String) : this(){
        this.imageSrc = src
        this.roomName = name
        this.price = price
        this.roomStructure = roomStructure
        this.option = option
        this.floorHeight = floorHeight
    }

    private fun parse(attrs: Set<String>){
        for(strings  in attrs){
            val arr = strings.split(delimiter)
        }
    }

    private fun makeStringSet() : Set<String>?{
        var favoriteSet: MutableSet<String> = mutableSetOf()
        lateinit var tempImageSrc: String
        if(imageSrc is Drawable){
            tempImageSrc = imageSrcHeader + delimiter + this.imageSrc.toString()
        }
        else if(imageSrc is Uri){
            tempImageSrc = imageSrcHeader + delimiter + this.imageSrc.toString()
        }
        else if(imageSrc is String){
            tempImageSrc = imageSrcHeader + delimiter + this.imageSrc
        }
        else{
            return null
        }
        favoriteSet.add(tempImageSrc)
        favoriteSet.add(roomNameHeader + delimiter + roomName)
        favoriteSet.add(priceHeader + delimiter + price)
        favoriteSet.add(roomStructureHeader + delimiter + roomStructure)
        favoriteSet.add(optionHeader + delimiter + option)
        favoriteSet.add(floorHeightHeader + delimiter + floorHeight)

        return favoriteSet
    }

}