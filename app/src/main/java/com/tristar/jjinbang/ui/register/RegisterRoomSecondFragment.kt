package com.tristar.jjinbang.ui.register

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.tristar.jjinbang.R
import com.tristar.jjinbang.ui.setting.login.SignUpFragmentDirections
import kotlinx.android.synthetic.main.register_room_first.*
import kotlinx.android.synthetic.main.register_room_second.*
import kotlinx.android.synthetic.main.sign_up_fragment.*

class RegisterRoomSecondFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.register_room_second, container, false)
    }

    private var doubleBackToExitPressedOnce : Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val inputMethodManager : InputMethodManager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        // 뒤로가기
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            inputMethodManager.hideSoftInputFromWindow(sign_up_name.windowToken, 0)
            inputMethodManager.hideSoftInputFromWindow(sign_up_id.windowToken, 0)
            inputMethodManager.hideSoftInputFromWindow(sign_up_password.windowToken, 0)
            inputMethodManager.hideSoftInputFromWindow(sign_up_confirm_password.windowToken, 0)

            // Handle the back button event
            if(doubleBackToExitPressedOnce){
                Navigation.findNavController(requireActivity(), R.id.fragment_container).navigate(
                    RegisterRoomSecondFragmentDirections.actionRegisterRoomSecondFragmentToMainFragment()
                )
            }
            doubleBackToExitPressedOnce = true
            Toast.makeText(context, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()

            Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
        }
        var deposit: Float = -1f
        // EditText
        edit_deposit.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                deposit = p0.toString().toFloat()
            }
        })
        var detailAddress: String? = null
        edit_detailAddress.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                detailAddress = p0.toString()
            }
        })
        var maintenance: Float = -1f
        edit_maintenance.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                maintenance = p0.toString().toFloat()
            }
        })
        var rent: Float = -1f
        edit_rent.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                rent = p0.toString().toFloat()
            }
        })
        var size: Float = -1f
        edit_size.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                size = p0.toString().toFloat()
            }
        })
        var title: String? = null
        edit_title.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                title = p0.toString()
            }
        })
        var detail: String? = null
        edit_detail.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                detail = p0.toString()
            }
        })

        checkbox_fullOptions.setOnClickListener {
            checkbox_airConditioner.toggle()
            checkbox_closet.toggle()
            checkbox_deskChair.toggle()
            checkbox_gasRange.toggle()
            checkbox_refrigerator.toggle()
            checkbox_washingMachine.toggle()
        }

        //btn
        btn_addressSearch.setOnClickListener {
            Navigation.findNavController(requireActivity(), R.id.fragment_container).navigate(
                RegisterRoomSecondFragmentDirections.actionRegisterRoomSecondFragmentToAddrWebviewFragment()
            )
        }

        btn_cancel.setOnClickListener {

        }

        btn_register.setOnClickListener {

        }
    }
}