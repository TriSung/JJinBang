package com.tristar.jjinbang

import android.content.SharedPreferences

class FavoriteRoom : FavoriteRoomAttribute {

    init{ }

    constructor(srcType: String, src: String, name: String, price: String, roomStructure: String, option: String, floorHeight: String) {
        when (srcType) {
            imageSrcHeaderDrawable -> {
                this.thumbnail = drawableString + src
            }
            imageSrcHeaderURI -> {
                this.thumbnail = src
            }
            imageSrcHeaderString -> {
                this.thumbnail = src
            }
        }
        this.roomTitle = name
        this.price = price
        this.roomStructure = roomStructure
        this.options = option
        this.floorHeight = floorHeight
    }

    constructor(pref: SharedPreferences) {
        readPrefInfo(pref)
    }

    private fun readPrefInfo(pref: SharedPreferences){
        when {
            pref.getString(imageSrcHeaderString, null) != null -> {
                this.thumbnail = pref.getString(imageSrcHeaderString, null)!!
            }
            pref.getString(imageSrcHeaderURI, null) != null -> {
                this.thumbnail = pref.getString(imageSrcHeaderURI, null)!!
            }
            pref.getString(imageSrcHeaderDrawable, null) != null -> {
                this.thumbnail = pref.getString(imageSrcHeaderDrawable, null)!!
            }
        }


        this.roomTitle = pref.getString(roomNameHeader, "")!!
        this.roomStructure = pref.getString(roomStructureHeader, "")!!
        this.options = pref.getString(optionHeader, "")!!
        this.price = pref.getString(priceHeader, "")!!
        this.floorHeight = pref.getString(floorHeightHeader, "")!!
    }

    fun saveInfo(pref: SharedPreferences){
        val prefEditor: SharedPreferences.Editor = pref.edit()

        when(this.thumbnail){
            is String ->{
                prefEditor.putString(imageSrcHeaderString, this.thumbnail as String)
            }
        }

        prefEditor.putString(roomNameHeader, this.roomTitle)
        prefEditor.putString(roomStructureHeader, this.roomStructure)
        prefEditor.putString(priceHeader, this.price)
        prefEditor.putString(optionHeader, this.options)
        prefEditor.putString(floorHeightHeader, this.floorHeight)

        prefEditor.apply()

    }

    private fun parse(attrs: Set<String>){
        for(strings  in attrs){
            val arr = strings.split(delimiter)
        }
    }
}