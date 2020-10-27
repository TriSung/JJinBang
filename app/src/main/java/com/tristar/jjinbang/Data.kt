package com.tristar.jjinbang

class Data{
    companion object{
        const val favoritePrefHeader : String = "FAVORITE"
        const val registerPrefHeader : String = "REGISTER"
    }
    var favoriteRoomList: MutableList<FavoriteRoomAttribute> = mutableListOf()
    var registeredRoomList: MutableList<RoomAttributes> = mutableListOf()

    var isFirstCreate : Boolean = true
    var autoLogin: Boolean = false

    lateinit var userId: String
    lateinit var userPass: String
}