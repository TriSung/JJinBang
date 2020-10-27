package com.tristar.jjinbang.ui.main

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.AttributeSet
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.Navigation
import com.tristar.jjinbang.Data
import com.tristar.jjinbang.FavoriteRoomAttribute
import com.tristar.jjinbang.MainActivity.Companion.mainData
import com.tristar.jjinbang.R
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

        /** Set searchView's text color white **/
        val textView = main_searchView.findViewById<EditText>(androidx.appcompat.R.id.search_src_text)
        textView.setTextColor(Color.WHITE)

        /** add button onClickListener **/
        main_setting.setOnClickListener {
            Navigation.findNavController(requireActivity(), R.id.fragment_container).navigate(
                MainFragmentDirections.actionMainFragmentToSettingFragment()
            )
        }

        /**
         * 1. read favorite list
         * 2. set favorite list
         */
        var ct: Int = 0
        for(roomAttr: FavoriteRoomAttribute in mainData.favoriteRoomList){
            if(ct % 2 == 0){
                val newFavoriteWidget = FavoriteWidget(requireContext())
                newFavoriteWidget.setAttributes(roomAttr)
                main_linear_favorite.addView(newFavoriteWidget)
            }
            else{
                val newFavoriteWidget = FavoriteWidget(requireContext())
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