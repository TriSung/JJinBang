package com.tristar.jjinbang

import java.util.*

open class RoomAttributes {
    lateinit var imageSrc: Any
    lateinit var roomName: String
    lateinit var price: String
    lateinit var roomStructure: String
    lateinit var option: String
    lateinit var floorHeight: String
    lateinit var dateOfMovingIn: String
    lateinit var direction: String
    lateinit var address: String
    lateinit var details:  String
    lateinit var maintenanceFee: String
    lateinit var registeredPerson: String

    var size: Float = 0.0f
    var parking: Boolean = false
    var elevator: Boolean = false

}