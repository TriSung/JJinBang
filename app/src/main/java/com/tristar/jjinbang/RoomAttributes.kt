package com.tristar.jjinbang

import com.google.firebase.database.FirebaseDatabase

class RoomAttributes {
    companion object{
        const val room1: String = "원룸"
        const val room2: String = "투룸"
        const val roomOver: String = "투룸 이상"
        const val roomSplit: String = "분리형 원룸"

        const val floorUnder : String= "반지하"
        const val floor1 : String = "1층"
        const val floor2 : String = "2층"
        const val floor3 : String = "3층"
        const val floor4 : String = "4층"
        const val floorOver: String = "5층 이상"
    }
    lateinit var registeredPerson: String

    /** 옵션 항목 */
    var isFullOption: Boolean = false // True:풀옵션
    var parking: Boolean = false
    var washingMachine: Boolean = false
    var refrigerator: Boolean = false
    var airConditioner: Boolean = false
    var closet: Boolean = false
    var deskChair: Boolean = false
    var gasRange: Boolean = false

    /** 관리비 항목 */
    var maintenanceFee: Float = 0f
    var waterFee: Boolean = false
    var electronicFee: Boolean = false
    var internetFee: Boolean = false
    var gasFee: Boolean = false


    /**
     * 집 정보
     */
//    lateinit var adminArea: String // 도, 특별시, 광역시
//    var locality: String? = null// 시, 군
//    var subLocality: String? = null// 구
//    lateinit var thoroughfare: String // 동
//    lateinit var subThoroughfare: String // 번지수
//    lateinit var detailAddress: String // 세부
    lateinit var address: String
    var detailAddress: String? = null

    var roomLatitude: Double = 0.0
    var roomLongitude: Double = 0.0

    var thumbnail: Any? = null
    lateinit var roomTitle: String

    var deposit: Float = 0f // 보증금
    var rent: Float = 0f// 월세
    var roomSize: Float = 0f

    lateinit var roomStructure: String // 방 구조


    fun saveDb(){
        var myRef = Data.db.reference.child("test1").child("address")
        myRef.setValue(address)
        myRef = Data.db.reference.child("test1").child("detailAddress")
        myRef.setValue(detailAddress)

    }
}