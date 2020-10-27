package com.tristar.jjinbang.ui.setting.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.tristar.jjinbang.R
import com.tristar.jjinbang.ui.setting.SettingFragment
import kotlinx.android.synthetic.main.login_fragment.*

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