package com.tristar.jjinbang.ui.setting.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.tristar.jjinbang.Data
import com.tristar.jjinbang.MainActivity
import com.tristar.jjinbang.R
import com.tristar.jjinbang.ui.TempUserData
import com.tristar.jjinbang.ui.Users
import com.tristar.jjinbang.ui.setting.SettingFragment
import kotlinx.android.synthetic.main.login_fragment.*

/**
 * Login
 * Id format: need to start alphabet, using only digit , alphabet
 * password format: need to contain a alphabet, digits, at least 8 characters
 */

class LoginFragment : Fragment() {
    companion object{
        fun newInstance() = LoginFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    private lateinit var inputId: String
    private lateinit var inputPassword: String
    private var isAutoLogin: Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        login_forgot.setOnClickListener { setLoginForgotBtnClickListener() }
        login_sign_up.setOnClickListener { setLoginSignUpBtnClickListener() }
        login_login.setOnClickListener { setLoginLoginBtnClickListener() }
        isAutoLogin = login_auto.isChecked
    }

    private fun setLoginLoginBtnClickListener(){
        inputId = login_id.text.toString()
        inputPassword = login_password.text.toString()

        // check id-password set
        /**
         * 임시 코드
         */
        val userInfo = checkId(inputId)
        if(userInfo == null){
            login_error_text.setText(R.string.login_wrong_info)
        }
        else{
            if(userInfo.userPassword != inputPassword){
                login_error_text.setText(R.string.login_wrong_info)
            }
            else{
                if(login_auto.isChecked){
                    Data.autoLogin = true
                    Data.userName = userInfo.userName
                    Data.userId = userInfo.userId
                    Data.userPassword = userInfo.userPassword
                    MainActivity.saveMainPref()
                }
                Data.isLogin = true
                Navigation.findNavController(requireActivity(), R.id.fragment_container).navigate(
                    LoginFragmentDirections.actionLoginFragmentToMainFragment()
                )
                Toast.makeText(context, userInfo.userName +"님 환영합니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkId(inputId: String): TempUserData?{
        for(user in Users.userList){
            if(inputId == user.userId)
                return user
        }
        return null
    }


    private fun setLoginForgotBtnClickListener(){
        Navigation.findNavController(requireActivity(), R.id.fragment_container).navigate(
            LoginFragmentDirections.actionLoginFragmentToForgotPassFragment()
        )
    }

    private fun setLoginSignUpBtnClickListener(){
        Navigation.findNavController(requireActivity(), R.id.fragment_container).navigate(
            LoginFragmentDirections.actionLoginFragmentToPhoneAuthFragment()
        )
    }
}