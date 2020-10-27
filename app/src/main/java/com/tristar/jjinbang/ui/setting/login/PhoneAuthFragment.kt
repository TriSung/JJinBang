package com.tristar.jjinbang.ui.setting.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.tristar.jjinbang.Data
import com.tristar.jjinbang.R
import kotlinx.android.synthetic.main.phone_auth_fragment.*

class PhoneAuthFragment : Fragment() {
    companion object{
        fun newInstance() = PhoneAuthFragment()
    }

    private lateinit var phoneNumber: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.phone_auth_fragment, container, false)
    }

    private var correctPhoneNum: Boolean = false
    private var authString: String? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            // Handle the back button event
            // show warning
            setLoginBtnClickListener()
        }

        phone_auth_first.setOnClickListener { setFirstBtnClickListener() }
        phone_auth_second.setOnClickListener { setSecondBtnClickListener() }
        phone_auth_login.setOnClickListener { setLoginBtnClickListener() }
        phone_auth_next.setOnClickListener { setNextBtnClickListener() }

        phone_auth_phone_number.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(p0: Editable?) { }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(Data.phoneNumberCheck(p0.toString()) and (Data.userPhoneNum == p0.toString())){
                    phone_auth_text.text = ""
                    correctPhoneNum = true
                }
                else{
                    phone_auth_text.text= resources.getString(R.string.phone_num_warning)
                    phone_auth_text.setTextColor(resources.getColor(R.color.color_warning, null))
                    correctPhoneNum = false
                }
            }
        })

        phone_auth_check_number.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                authString = p0.toString()
            }
        })
    }

    private fun setFirstBtnClickListener(){
        if(correctPhoneNum){
            phone_auth_next.visibility = View.VISIBLE
            phone_auth_phone_number.isEnabled = false
        }
    }

    private fun setSecondBtnClickListener(){
        if(Data.authString != null){
            if(Data.authString == authString){
                phone_auth_next.visibility = View.VISIBLE
                phone_auth_second.visibility = View.INVISIBLE
                phone_auth_check_number.isEnabled = false
            }
        }
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