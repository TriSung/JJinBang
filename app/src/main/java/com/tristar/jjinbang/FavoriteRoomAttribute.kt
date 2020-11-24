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

open class FavoriteRoomAttribute{
    var thumbnail: Any? = null
    lateinit var roomTitle: String
    lateinit var price: String
    lateinit var roomStructure: String
    lateinit var options: String
    lateinit var floorHeight: String

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
}