package com.tristar.jjinbang

import android.content.SharedPreferences

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
        const val imageSrcHeaderDrawable : String = "IMAGESRC_DRAWABLE"
        const val imageSrcHeaderURI: String = "IMAGESRC_URI"
        const val imageSrcHeaderString: String = "IMAGESRC_STRING"

        const val roomNameHeader : String = "ROOMNAME"
        const val priceHeader : String = "PRICE"
        const val roomStructureHeader: String = "ROOMSTRUCTURE"
        const val optionHeader : String = "OPTION"
        const val floorHeightHeader : String = "FLOORHEIGHT"

        const val drawableString = "android.resource://" + "com.tristar.jjinbang"

        const val delimiter: String = "_"
    }
    init{ }

    constructor(srcType: String, src: String, name: String, price: String, roomStructure: String, option: String, floorHeight: String) : this(){
        when{
            srcType == imageSrcHeaderDrawable->{
                this.imageSrc = drawableString + src
            }
            srcType == imageSrcHeaderURI->{
                this.imageSrc = src
            }
            srcType == imageSrcHeaderString->{
                this.imageSrc = src
            }
        }
        this.roomName = name
        this.price = price
        this.roomStructure = roomStructure
        this.option = option
        this.floorHeight = floorHeight
    }

    constructor(pref: SharedPreferences) : this(){
        readPrefInfo(pref)
    }

    private fun readPrefInfo(pref: SharedPreferences){
        when {
            pref.getString(imageSrcHeaderString, null) != null -> {
                this.imageSrc = pref.getString(imageSrcHeaderString, null)!!
            }
            pref.getString(imageSrcHeaderURI, null) != null -> {
                this.imageSrc = pref.getString(imageSrcHeaderURI, null)!!
            }
            pref.getString(imageSrcHeaderDrawable, null) != null -> {
                this.imageSrc = pref.getString(imageSrcHeaderDrawable, null)!!
            }
        }

        this.roomName = pref.getString(roomNameHeader, null)!!
        this.roomStructure = pref.getString(roomStructureHeader, null)!!
        this.option = pref.getString(optionHeader, null)!!
        this.price = pref.getString(priceHeader, null)!!
        this.floorHeight = pref.getString(floorHeightHeader, null)!!
    }

    fun saveInfo(pref: SharedPreferences){
        val prefEditor: SharedPreferences.Editor = pref.edit()

        when(this.imageSrc){
            is String ->{
                prefEditor.putString(imageSrcHeaderString, this.imageSrc as String)
            }
        }

        prefEditor.putString(roomNameHeader, this.roomName)
        prefEditor.putString(roomStructureHeader, this.roomStructure)
        prefEditor.putString(priceHeader, this.price)
        prefEditor.putString(optionHeader, this.option)
        prefEditor.putString(floorHeightHeader, this.floorHeight)

        prefEditor.apply()

    }

    private fun parse(attrs: Set<String>){
        for(strings  in attrs){
            val arr = strings.split(delimiter)
        }
    }

}