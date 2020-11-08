package com.tristar.jjinbang

import android.content.SharedPreferences
import com.tristar.jjinbang.ui.TempUserData
import com.tristar.jjinbang.ui.Users
import java.util.regex.Pattern

class Data{
    companion object{
        var isDataLoaded: Boolean = false
        const val favoritePrefHeader : String = "FAVORITE_FILE_SET"
        const val registerPrefHeader : String = "REGISTER_FILE_SET"
        const val isRegisteredUserHeader: String = "REGISTERED_USER"
        const val userNameHeader: String = "USER_NAME"
        const val userIdHeader: String = "USER_ID"
        const val userPasswordHeader: String = "USER_PASSWORD"
        const val isAutoHeader: String = "AUTO_LOGIN"

        private val passReg = Regex("^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[\$@\$!%*#?&]).{8,15}.\$")
        private val idReg = Regex("^[a-zA-Z][a-zA-Z0-9]{5,15}\$")
        private val phoneNumberReg = Regex("^01[016789][0-9]{4}[0-9]{4}\$")
        fun passwordCheck(password: String) : Boolean = passReg.matches(password)
        fun idCheck(id: String) : Boolean = idReg.matches(id)
        fun phoneNumberCheck(phoneNum : String) : Boolean = phoneNumberReg.matches(phoneNum)

        var hardwarePhoneNum: String? = null
        var userName: String? = null
        var userId: String? = null
        var userPassword: String? = null

        var isLogin: Boolean = false

        var isRegisteredUser: Boolean = false

        var favoriteRoomList: MutableList<FavoriteRoomAttribute> = mutableListOf()
        var registeredRoomList: MutableList<RoomAttributes> = mutableListOf()

        var autoLogin: Boolean = false

        fun sendNewUserInfo(){
            /**
             * test code
             */
            Users.userList.add(TempUserData(userName!!, userId!!, userPassword!!))
        }
        val options = Options()
    }
}

class Options{

}