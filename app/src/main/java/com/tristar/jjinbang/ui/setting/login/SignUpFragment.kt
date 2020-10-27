package com.tristar.jjinbang.ui.setting.login

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.content.getSystemService
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.tristar.jjinbang.Data
import com.tristar.jjinbang.R
import com.tristar.jjinbang.ui.setting.SettingFragment
import kotlinx.android.synthetic.main.sign_up_fragment.*

class SignUpFragment : Fragment(){

    companion object{
        fun newInstance() = SignUpFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.sign_up_fragment, container, false)
    }

    private var userName: String? = null
    private var userId: String? = null
    private var isDuplicated : Boolean = false
    private var userPassword: String? = null
    private var isPasswordInputEnd: Boolean = false
    private var isValidPassword: Boolean = false
    private var passwordConfirmed: Boolean = false

    private var doubleBackToExitPressedOnce : Boolean = false




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val inputMethodManager : InputMethodManager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            inputMethodManager.hideSoftInputFromWindow(sign_up_name.windowToken, 0)
            inputMethodManager.hideSoftInputFromWindow(sign_up_id.windowToken, 0)
            inputMethodManager.hideSoftInputFromWindow(sign_up_password.windowToken, 0)
            inputMethodManager.hideSoftInputFromWindow(sign_up_confirm_password.windowToken, 0)

            // Handle the back button event
            if(doubleBackToExitPressedOnce){
                Navigation.findNavController(requireActivity(), R.id.fragment_container).navigate(
                    SignUpFragmentDirections.actionSignUpFragmentToLoginFragment()
                )
            }
            doubleBackToExitPressedOnce = true
            Toast.makeText(context, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()

            Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
        }

        sign_up.setOnClickListener { setSignUpBtnClickListener() }
        sign_up_check_duplication.setOnClickListener { setSignUpDuplicateCheckBtnListener() }

        sign_up_name.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) { }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                userName = p0.toString()
            }
        })

        sign_up_id.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) { }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(Data.idCheck(p0.toString())){
                    userId = p0.toString()
                    sign_up_check_duplication.isClickable = true
                    sign_up_id_text.setText(R.string.duplication_check)
                    sign_up_id_text.setTextColor(resources.getColor(R.color.color_warning, null))
                }
            }
        })

        sign_up_password.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
                isPasswordInputEnd = true
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                isPasswordInputEnd = false
                isValidPassword = if(Data.passwordCheck(p0.toString())){
                    sign_up_password_text.setText(R.string.usable_password)
                    sign_up_password_text.setTextColor(resources.getColor(R.color.background_main, null))
                    userPassword = p0.toString()
                    true
                } else{
                    sign_up_password_text.setText(R.string.error_password)
                    sign_up_password_text.setTextColor(resources.getColor(R.color.color_warning, null))
                    userPassword = null
                    false
                }
            }
        })

        sign_up_confirm_password.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(p0: Editable?) { }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(isPasswordInputEnd){
                    passwordConfirmed = if(isValidPassword and (p0.toString() == userPassword!!)){
                        sign_up_confirm_password_text.setText(R.string.correct_password_confirm)
                        sign_up_confirm_password_text.setTextColor(resources.getColor(R.color.background_main, null))
                        true
                    } else{
                        sign_up_confirm_password_text.setText(R.string.warning_password_confirm)
                        sign_up_confirm_password_text.setTextColor(resources.getColor(R.color.color_warning, null))
                        false
                    }
                }
                else{
                    sign_up_confirm_password_text.text = ""
                }
            }
        })
    }

    private fun setSignUpBtnClickListener(){
        if(userName == null){
            Toast.makeText(context, "이름을 입력해주세요", Toast.LENGTH_SHORT).show()
        }
        else{
            if(isDuplicated){
                Toast.makeText(context, "아이디가 중복되었습니다. 다시 확인해주세요", Toast.LENGTH_SHORT).show()
            }
            else{
                if(!isValidPassword){
                    Toast.makeText(context, "비밀번호를 다시 확인해주세요", Toast.LENGTH_SHORT).show()
                }
                else{
                    Data.userId = userId!!
                    Data.userPass = userPassword!!
                    Data.userName = userName!!

                    Navigation.findNavController(requireActivity(), R.id.fragment_container).navigate(
                        SignUpFragmentDirections.actionSignUpFragmentToLoginFragment()
                    )
                }
            }
        }
    }

    private fun setSignUpDuplicateCheckBtnListener(){
        // 디비에 있는 id와 비교, 중복된 값이 있으면 안됨
        if(!isDuplicated){
            sign_up_id_text.setText(R.string.usable_id)
            sign_up_id_text.setTextColor(resources.getColor(R.color.background_main, null))
            sign_up_id.isEnabled = false
            sign_up_check_duplication.isClickable = false
        }
        else{
            sign_up_id_text.setText(R.string.duplicated_id)
            sign_up_id_text.setTextColor(resources.getColor(R.color.color_warning, null))
        }
    }
}