package com.tristar.jjinbang

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        container = findViewById(R.id.fragment_container)

        Data.favoriteRoomList.add(0, FavoriteRoomAttribute(
            ResourcesCompat.getDrawable(resources, R.drawable.room_favorite, null)!!, "더스테이1", "5000/50", "1.5룸",
            "풀옵션", "2층"
        ))

        Data.favoriteRoomList.add(1, FavoriteRoomAttribute(
            ResourcesCompat.getDrawable(resources, R.drawable.room_favorite2, null)!!, "더스테이2", "5000/50", "1.5룸",
            "풀옵션", "3층"
        ))
    }

    companion object{
        val mainData: Data = Data()
    }
}