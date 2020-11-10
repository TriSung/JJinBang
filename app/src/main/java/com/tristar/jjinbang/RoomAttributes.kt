package com.tristar.jjinbang

open class RoomAttributes {
    val room1: String = "원룸"
    val room2: String = "투룸"
    val roomOver: String = "투룸 이상"
    val roomSplit: String = "분리형 원룸"

    val floorUnder : String= "반지하"
    val floor1 : String = "1층"
    val floor2 : String = "2층"
    val floor3 : String = "3층"
    val floor4 : String = "4층"
    val floorOver: String = "5층 이상"

    var thumbnail: Any? = null
    lateinit var roomTitle: String
    lateinit var price: String

    var deposit: Int = 0 // 보증금
    var rent: Int = 0// 월세

    lateinit var roomStructure: String // 방 구조

    var isFullOption: Boolean = false // True:풀옵션
    lateinit var options: String

    lateinit var floorHeight: String

//    lateinit var adminArea: String // 도, 특별시, 광역시
//    lateinit var locality: String // 시, 군
//    lateinit var subLocality: String // 구
//    lateinit var thoroughfare: String // 동
//    lateinit var subThoroughfare: String // 번지수
//    lateinit var detailAddress: String // 세부
//
//    var roomLatitude: Double = 0.0
//    var roomLongitude: Double = 0.0
//
//    lateinit var maintenanceFee: String
//
//    lateinit var registeredPerson: String
//
//    var parking: Boolean = false
}