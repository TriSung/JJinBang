package com.tristar.jjinbang

import java.util.regex.Pattern

class Data{
    companion object{
        const val favoritePrefHeader : String = "FAVORITE"
        const val registerPrefHeader : String = "REGISTER"
        private val passReg = Regex("^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[\$@\$!%*#?&]).{8,15}.\$")
        private val idReg = Regex("^[a-zA-Z].[a-zA-Z0-9]{5,15}$")
        private val phoneNumberReg = Regex("^01[016789][0-9]{4}[0-9]{4}$")
        fun passwordCheck(password: String) : Boolean = passReg.matches(password)
        fun idCheck(id: String) : Boolean = idReg.matches(id)
        fun phoneNumberCheck(phoneNum : String) : Boolean = phoneNumberReg.matches(phoneNum)

        lateinit var userPhoneNum: String
        lateinit var userName: String
        lateinit var userId: String
        lateinit var userPass: String

        var isLogin: Boolean = false

        var favoriteRoomList: MutableList<FavoriteRoomAttribute> = mutableListOf()
        var registeredRoomList: MutableList<RoomAttributes> = mutableListOf()

        var isFirstCreate : Boolean = true
        var autoLogin: Boolean = false

        var authString: String? = "1234"
    }

}