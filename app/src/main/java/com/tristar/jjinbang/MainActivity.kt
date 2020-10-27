package com.tristar.jjinbang

import android.Manifest
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.TelephonyManager
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.widget.FrameLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.tristar.jjinbang.ui.main.MainFragment

class MainActivity : AppCompatActivity() {
    private lateinit var container: FrameLayout
    private val sharedPrefFile = "app_preferences"
    private lateinit var pref: SharedPreferences

    private val REQUEST_READ_PHONE_STATE = 1
    private val REQUEST_READ_PHONE_NUMBER = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val telManager = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_SMS
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_PHONE_NUMBERS
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_PHONE_STATE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        Data.userPhoneNum = telManager.line1Number
        if(Data.userPhoneNum.startsWith("+82")){
            Data.userPhoneNum = Data.userPhoneNum.replace("+82", "0")
        }

        container = findViewById(R.id.fragment_container)

        pref = getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)

        // 프로그램이 처음 켜질때 한번만 수행하게 변경
        var attrs: MutableSet<String> = mutableSetOf()
        pref.getStringSet("FAVORITE", attrs)

        Data.favoriteRoomList.add(0, FavoriteRoomAttribute(
            ResourcesCompat.getDrawable(resources, R.drawable.room_favorite, null)!!, "더스테이1", "5000/50", "1.5룸",
            "풀옵션", "2층"
        ))

        Data.favoriteRoomList.add(1, FavoriteRoomAttribute(
            ResourcesCompat.getDrawable(resources, R.drawable.room_favorite2, null)!!, "더스테이2", "5000/50", "1.5룸",
            "풀옵션", "3층"
        ))
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

    private fun readPreference(){
        if(Data.isFirstCreate){

        }
        Data.isFirstCreate = false
    }

    override fun onDestroy(){
        super.onDestroy()

    }

    companion object{
        val mainData: Data = Data()

    }
}