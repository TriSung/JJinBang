package com.tristar.jjinbang.ui.setting.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.tristar.jjinbang.R
import kotlinx.android.synthetic.main.phone_auth_fragment.*

class PhoneAuthFragment : Fragment() {
    companion object{
        fun newInstance() = PhoneAuthFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.phone_auth_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            // Handle the back button event
            // show warning
            setLoginBtnClickListener()
        }

        phone_auth_first.setOnClickListener {  }
        phone_auth_second.setOnClickListener {  }
        phone_auth_login.setOnClickListener { setLoginBtnClickListener() }
        phone_auth_next.setOnClickListener { setNextBtnClickListener() }
    }

    private fun setNextBtnClickListener(){
        Navigation.findNavController(requireActivity(), R.id.fragment_container).navigate(
            PhoneAuthFragmentDirections.actionPhoneAuthFragmentToSignUpFragment()
        )
    }

    private fun setLoginBtnClickListener(){
        Navigation.findNavController(requireActivity(), R.id.fragment_container).navigate(
            PhoneAuthFragmentDirections.actionPhoneAuthFragmentToLoginFragment()
        )
    }
}