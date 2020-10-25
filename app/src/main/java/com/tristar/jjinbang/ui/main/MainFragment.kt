package com.tristar.jjinbang.ui.main

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.navigation.Navigation
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

        main_setting.setOnClickListener {
            Navigation.findNavController(requireActivity(), R.id.fragment_container).navigate(
                MainFragmentDirections.actionMainFragmentToSettingFragment()
            )
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}