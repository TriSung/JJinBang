package com.tristar.jjinbang

import android.Manifest
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.TelephonyManager
import android.util.Log
import android.widget.FrameLayout
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.tristar.jjinbang.ui.TempUserData
import com.tristar.jjinbang.ui.Users
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    private lateinit var container: FrameLayout

    private val REQUEST_READ_PHONE_NUMBER = 1
    private val REQUEST_READ_PHONE_STATE = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        checkAllPermissions()

        val telManager = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.READ_SMS
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this, Manifest.permission.READ_PHONE_NUMBERS
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this, Manifest.permission.READ_PHONE_STATE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        Data.hardwarePhoneNum = telManager.line1Number
        if(Data.hardwarePhoneNum?.startsWith("+82")!!){
            Data.hardwarePhoneNum = Data.hardwarePhoneNum?.replace("+82", "0")
        }
        container = findViewById(R.id.fragment_container)
        activity = this

        pref = getSharedPreferences(mainSharedPrefFile, Context.MODE_PRIVATE)

        loadPreference(pref!!)

        /**
         * test code
         */

        Users.userList.add(TempUserData("허정우", "hjw123", "hjw12345!"))
        Users.userList.add(TempUserData("박상우", "psw123", "caucse12!"))
    }

    private fun loadPreference(pref: SharedPreferences){
        if(!Data.isDataLoaded){
            Data.autoLogin = pref.getBoolean(Data.isAutoHeader, false)
            if(Data.autoLogin){
                Data.userName = pref.getString(Data.userNameHeader, null)
                Data.userId = pref.getString(Data.userIdHeader, null)
                Data.userPassword = pref.getString(Data.userPasswordHeader, null)
                Data.isLogin = true
                Toast.makeText(applicationContext, Data.userName + "님 어서오세요", Toast.LENGTH_SHORT).show()
                /**
                 * test code
                 */
                Users.userList.add(TempUserData(Data.userName!!, Data.userId!!, Data.userPassword!!))

                Data.isRegisteredUser = pref.getBoolean(Data.isRegisteredUserHeader, false)
                if(Data.isRegisteredUser){
                    var registeredRoomFileList: MutableSet<String> =
                        pref.getStringSet(Data. registerPrefHeader, setOf("")) as MutableSet<String>
                }
            }

            val favoriteRoomFileList: MutableSet<String> =
                pref.getStringSet(Data.favoritePrefHeader, setOf("null")) as MutableSet<String>

            if(favoriteRoomFileList != null){
                for(fileName: String in favoriteRoomFileList){
                    if(fileName == "null")
                        continue
                    val favoritePref = getSharedPreferences(fileName, Context.MODE_PRIVATE)
                    val favoriteRoomAttr : FavoriteRoomAttribute = FavoriteRoomAttribute(favoritePref)
                    Data.favoriteRoomList.add(favoriteRoomAttr)
                }
            }
        }
        Data.isDataLoaded = true
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            REQUEST_READ_PHONE_STATE -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //We now have permission, restart the app
                    val intent = getIntent()
                    finish()
                    startActivity(intent)
                } else {
                }
                return
            }
            REQUEST_READ_PHONE_NUMBER -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //We now have permission, restart the app
                    val intent = getIntent()
                    finish()
                    startActivity(intent)
                } else {
                }
                return
            }
        }
    }

    private fun checkAllPermissions() : Boolean{
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
            != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.READ_PHONE_STATE),
                REQUEST_READ_PHONE_STATE)
            return false
        }
        else if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_NUMBERS)
            != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.READ_PHONE_NUMBERS),
                REQUEST_READ_PHONE_NUMBER)
            return false
        }
        return true
    }

    override fun onDestroy(){
        saveMainPref()
        super.onDestroy()

    }

    companion object{
        var activity: MainActivity? = null

        private const val mainSharedPrefFile = "TriStar.Jjinbang.Main"
        private var pref: SharedPreferences? = null

        fun saveMainPref(){
            if(pref != null){
                val prefEditor: SharedPreferences.Editor = pref?.edit()!!
                var ct: Int = 0
                val favoriteRoomFileSet: MutableSet<String> = mutableSetOf()
                for(favoriteRoomAttrs in Data.favoriteRoomList){
                    val fileName = Data.favoritePrefHeader + "_" + ct.toString()
                    favoriteRoomFileSet.add(fileName)
                    val pref = activity?.getSharedPreferences(fileName, Context.MODE_PRIVATE)!!
                    favoriteRoomAttrs.saveInfo(pref)
                    ct += 1
                }
                prefEditor.putStringSet(Data.favoritePrefHeader, favoriteRoomFileSet)
                prefEditor.putBoolean(Data.isAutoHeader, Data.autoLogin)
                if(Data.autoLogin){
                    prefEditor.putString(Data.userNameHeader, Data.userName)
                    prefEditor.putString(Data.userIdHeader, Data.userId)
                    prefEditor.putString(Data.userPasswordHeader, Data.userPassword)

                    prefEditor.putBoolean(Data.isRegisteredUserHeader, Data.isRegisteredUser)
                    if(Data.isRegisteredUser){
                        val registeredRoomFileSet: MutableSet<String> = mutableSetOf()
                        ct = 0
                        for(registeredRoomAttrs in Data.registeredRoomList){
                            val fileName = Data.registerPrefHeader + "_" + ct.toString()
                            registeredRoomFileSet.add(fileName)
                            val pref = activity?.getSharedPreferences(fileName, Context.MODE_PRIVATE)!!
                            ct += 1
                        }
                        prefEditor.putStringSet(Data.registerPrefHeader, registeredRoomFileSet)
                    }
                }
                prefEditor.commit()
                Log.d("TESTLOG", "saveMainPref: save end")
            }
        }
    }
}