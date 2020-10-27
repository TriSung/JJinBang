package com.tristar.jjinbang.ui.setting.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.tristar.jjinbang.R
import com.tristar.jjinbang.ui.setting.SettingFragment
import kotlinx.android.synthetic.main.forgot_password_fragment.*

class ForgotPassFragment : Fragment() {
    companion object{
        fun newInstance() = ForgotPassFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.forgot_password_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        forgot_sign_up.setOnClickListener { setForgotSignUpBtnClickListener() }
        forgot_send.setOnClickListener { setForgotSendBtnClickListener() }
    }

    private fun setForgotSignUpBtnClickListener(){
        Navigation.findNavController(requireActivity(), R.id.fragment_container).navigate(
            ForgotPassFragmentDirections.actionForgotPassFragmentToPhoneAuthFragment()
        )
    }

    private fun setForgotSendBtnClickListener(){
        // need to check email first
        Navigation.findNavController(requireActivity(), R.id.fragment_container).navigate(
            ForgotPassFragmentDirections.actionForgotPassFragmentToResetPasswordFragment()
        )
    }
}