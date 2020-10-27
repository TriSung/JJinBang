package com.tristar.jjinbang

import java.util.regex.Pattern

class Data{
    companion object{
        const val favoritePrefHeader : String = "FAVORITE"
        const val registerPrefHeader : String = "REGISTER"

        val passReg = Regex("^(?=.*[a-zA-Z0-9])(?=.*[a-zA-Z!@#\$%^&*])(?=.*[0-9!@#\$%^&*]).{8,15}\$")
        val idReg = Regex("^[a-zA-Z].[a-zA-Z0-9]{5,15}$")
        val phoneNumberReg = Regex("^01[016789][0-9]{4}[0-9]{4}$")

        fun passwordCheck(password: String) : Boolean{
            return passReg.matches(password)
        }

        fun idCheck(id: String) : Boolean{
            return idReg.matches(id)
        }

        fun phoneNumberCheck(phoneNum : String) : Boolean{
            return phoneNumberReg.matches(phoneNum)
        }
    }
    var favoriteRoomList: MutableList<FavoriteRoomAttribute> = mutableListOf()
    var registeredRoomList: MutableList<RoomAttributes> = mutableListOf()

    var isFirstCreate : Boolean = true
    var autoLogin: Boolean = false

    lateinit var userId: String
    lateinit var userPass: String
}