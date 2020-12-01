package com.tristar.jjinbang.ui.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.tristar.jjinbang.Data
import com.tristar.jjinbang.MainActivity
import com.tristar.jjinbang.R
import kotlinx.android.synthetic.main.setting_fragment.*

class SettingFragment : Fragment() {


    companion object{
        fun newInstance() = SettingFragment()
    }


    private val REQUEST_CODE = 0
    private val imageView: ImageView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.setting_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(Data.isLogin){
            setting_logout.visibility = View.VISIBLE
        }
        else{
            setting_logout.visibility = View.INVISIBLE
        }

        //setting_select_image.setOnClickListener{ setOnSelectimageListener() }
        setting_go_login.setOnClickListener{ setOnGoLoginBtnClickListener() }
        setting_back.setOnClickListener { setOnGoBackBtnClickListener() }
        setting_logout.setOnClickListener { setOnLogoutBtnClickListener() }

    }

    private fun setOnSelectimageListener(){
//        Navigation.findNavController(requireActivity(), R.id.fragment_container).navigate(
//            SettingFragmentDirections.actionSettingFragmentToSelectimage()
//        )
    }



    private fun setOnGoLoginBtnClickListener(){
        if(!Data.isLogin){
            Navigation.findNavController(requireActivity(), R.id.fragment_container).navigate(
                SettingFragmentDirections.actionSettingFragmentToLoginFragment()
            )
        }
    }

    private fun setOnGoBackBtnClickListener(){
        Navigation.findNavController(requireActivity(), R.id.fragment_container).popBackStack()
    }

    private fun setOnLogoutBtnClickListener(){
        Toast.makeText(context,"로그아웃 되었습니다.", Toast.LENGTH_SHORT).show()
        Data.isLogin = false
        Data.userId = null
        Data.userName = null
        Data.userPassword = null
        Data.autoLogin = false
        MainActivity.saveMainPref()
        setting_logout.visibility = View.INVISIBLE
    }
}