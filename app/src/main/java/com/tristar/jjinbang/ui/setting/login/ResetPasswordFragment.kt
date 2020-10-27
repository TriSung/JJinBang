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
import kotlinx.android.synthetic.main.reset_password_fragment.*

class ResetPasswordFragment : Fragment() {
    companion object{
        fun newInstance() = ResetPasswordFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.reset_password_fragment, container, false)
    }

    private lateinit var password: String
    private var passwordConfirmed: Boolean = false
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            // Handle the back button event
            // show warning
            // 비밀번호를 바꿔야 한다고 표시
        }

        reset_submit.setOnClickListener { setSubmitBtnClickListener() }

        reset_new_password.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(Data.passwordCheck(p0.toString())){
                    reset_password_text.setText(R.string.usable_password)
                    reset_password_text.setTextColor(resources.getColor(R.color.background_main, null))
                    password = p0.toString()
                }
                else{
                    reset_password_text.setText(R.string.error_password)
                    reset_password_text.setTextColor(resources.getColor(R.color.color_warning, null))
                }
            }
        })

        reset_password_confirm.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                passwordConfirmed = if(p0.toString() == password){
                    reset_password_confirm_text.setText(R.string.correct_password_confirm)
                    reset_password_confirm_text.setTextColor(resources.getColor(R.color.background_main, null))
                    true
                } else{
                    reset_password_confirm_text.setText(R.string.warning_password_confirm)
                    reset_password_confirm_text.setTextColor(resources.getColor(R.color.color_warning, null))
                    false
                }
            }
        })
    }

    private fun setSubmitBtnClickListener(){
        // send new password to server
        if(passwordConfirmed){
            Navigation.findNavController(requireActivity(), R.id.fragment_container).navigate(
                ResetPasswordFragmentDirections.actionResetPasswordFragmentToLoginFragment()
            )
        }
    }
}