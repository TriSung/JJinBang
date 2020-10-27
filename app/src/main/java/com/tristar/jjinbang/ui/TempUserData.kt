package com.tristar.jjinbang.ui

data class TempUserData(
    val userName: String,
    val userId: String,
    val userPassword: String
) {

}

class Users{
    companion object{
        val userList: MutableSet<TempUserData> = mutableSetOf()
    }
}