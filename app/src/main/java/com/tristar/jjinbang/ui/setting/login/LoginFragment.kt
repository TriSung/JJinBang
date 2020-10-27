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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        login_forgot.setOnClickListener { setLoginForgotBtnClickListener() }
        login_sign_up.setOnClickListener { setLoginSignUpBtnClickListener() }

        login_id.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                // 입력이 끝난 경우 작동
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // 타이핑중인 텍스트에 변화가 있으면 작동
            }
        })

        login_password.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
        })
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