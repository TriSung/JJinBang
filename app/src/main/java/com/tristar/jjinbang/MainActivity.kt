package com.tristar.jjinbang

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.widget.FrameLayout
import androidx.core.content.res.ResourcesCompat
import com.tristar.jjinbang.ui.main.MainFragment

class MainActivity : AppCompatActivity() {
    private lateinit var container: FrameLayout

    private val sharedPrefFile = "app_preferences"

    private lateinit var pref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        container = findViewById(R.id.fragment_container)

        pref = getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)

        // 프로그램이 처음 켜질때 한번만 수행하게 변경
        var attrs: MutableSet<String> = mutableSetOf()
        pref.getStringSet("FAVORITE", attrs)

        mainData.favoriteRoomList.add(0, FavoriteRoomAttribute(
            ResourcesCompat.getDrawable(resources, R.drawable.room_favorite, null)!!, "더스테이1", "5000/50", "1.5룸",
            "풀옵션", "2층"
        ))

        mainData.favoriteRoomList.add(1, FavoriteRoomAttribute(
            ResourcesCompat.getDrawable(resources, R.drawable.room_favorite2, null)!!, "더스테이2", "5000/50", "1.5룸",
            "풀옵션", "3층"
        ))
    }

    private fun readPreference(){
        if(mainData.isFirstCreate){

        }
        mainData.isFirstCreate = false
    }

    override fun onDestroy(){
        super.onDestroy()

    }

    companion object{
        val mainData: Data = Data()

    }
}