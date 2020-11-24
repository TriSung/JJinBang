package com.tristar.jjinbang.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.navigation.Navigation
import com.tristar.jjinbang.Data
import com.tristar.jjinbang.FavoriteRoomAttribute
import com.tristar.jjinbang.R
import com.tristar.jjinbang.ui.roomInfo.FavoriteWidget
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /** add button onClickListener **/
        main_setting.setOnClickListener {
            Navigation.findNavController(requireActivity(), R.id.fragment_container).navigate(
                MainFragmentDirections.actionMainFragmentToSettingFragment()
            )
        }
        
        main_register_room.setOnClickListener { 
            if(Data.isLogin){
                Toast.makeText(context, "준비중입니다.", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(context, "로그인이 필요한 서비스입니다.", Toast.LENGTH_SHORT).show()
            }
            Navigation.findNavController(requireActivity(), R.id.fragment_container).navigate(
                MainFragmentDirections.actionMainFragmentToRegisterRoomFirstFragment())
        }

        main_search.setOnClickListener {
            Navigation.findNavController(requireActivity(), R.id.fragment_container).navigate(
                MainFragmentDirections.actionMainFragmentToSearchFragment()
            )
        }

        /**
         * 1. read favorite list
         * 2. set favorite list
         */
        var ct: Int = 0
        for(roomAttr: FavoriteRoomAttribute in Data.favoriteRoomList){
            if(ct % 2 == 0){
                val newFavoriteWidget =
                    FavoriteWidget(
                        requireContext()
                    )
                newFavoriteWidget.setAttributes(roomAttr)
                main_linear_favorite.addView(newFavoriteWidget)
            }
            else{
                val newFavoriteWidget =
                    FavoriteWidget(
                        requireContext()
                    )
                newFavoriteWidget.setAttributes(roomAttr)
                main_linear_favorite2.addView(newFavoriteWidget)
            }

            ct += 1
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    private fun addNewFavorite(){

    }

}