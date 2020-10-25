package com.tristar.jjinbang.ui.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.tristar.jjinbang.R
import kotlinx.android.synthetic.main.setting_fragment.*

class SettingFragment : Fragment() {
    companion object{
        fun newInstance() = SettingFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.setting_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setting_go_login.setOnClickListener{setOnGoLoginBtnClickListener()}
        setting_back.setOnClickListener { setOnGoBackBtnClickListener() }
    }

    private fun setOnGoLoginBtnClickListener(){
        Navigation.findNavController(requireActivity(), R.id.fragment_container).navigate(
            SettingFragmentDirections.actionSettingFragmentToLoginFragment()
        )
    }

    private fun setOnGoBackBtnClickListener(){
        if(! Navigation.findNavController(requireActivity(), R.id.fragment_container).popBackStack()){
            finish()
        }
    }

    private fun finish(){

    }
}