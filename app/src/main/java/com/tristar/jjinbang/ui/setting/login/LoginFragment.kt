package com.tristar.jjinbang.ui.setting.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.tristar.jjinbang.Data
import com.tristar.jjinbang.R
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