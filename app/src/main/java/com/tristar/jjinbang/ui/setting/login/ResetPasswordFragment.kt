package com.tristar.jjinbang.ui.setting.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            // Handle the back button event
            // show warning
            setSubmitBtnClickListener()
        }

        reset_submit.setOnClickListener { setSubmitBtnClickListener() }
    }

    private fun setSubmitBtnClickListener(){
        Navigation.findNavController(requireActivity(), R.id.fragment_container).navigate(
            ResetPasswordFragmentDirections.actionResetPasswordFragmentToLoginFragment()
        )
    }
}