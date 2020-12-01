package com.tristar.jjinbang.ui.register

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.tristar.jjinbang.R
import com.tristar.jjinbang.RoomAttributes
import kotlinx.android.synthetic.main.register_room_second.*
import java.util.*

class RegisterRoomSecondFragment : Fragment() {
    companion object{
        public var address_: String? = null
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.register_room_second, container, false)
    }

    private var doubleBackToExitPressedOnce : Boolean = false
    private var getAddress: Boolean = false

    private var deposit_: Float = -1f
    private var maintenance_: Float = -1f
    private var rent_: Float = -1f
    private var size_: Float = -1f
    private var detailAddress_: String? = null
    private var title_: String? = null
    private var detail_: String? = null
    private var adminArea_: String? = null
    private var locality_: String? = null
    private var subLocality_: String? = null
    private var thoroughfare_: String? = null
    private var subThoroughfare_: String? = null
    val timer = Timer()

    val handler: Handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            // roomImage에 addView로 3D  뷰어 추가하면 됨
            roomImage?.removeAllViews()
            val imageView2 = ImageView(requireContext())
            imageView2.setImageResource(R.drawable.app_logo_color)
            val lp = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            roomImage.addView(imageView2)
            imageView2.layoutParams = lp
        }
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var imageView = ImageView(requireContext())
        val lp = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        roomImage.addView(imageView)
        imageView.layoutParams = lp
        Glide.with(this).load(R.raw.loading_animation).into(imageView)
        val fragment_ = this
        val timerTask : TimerTask = object : TimerTask(){
            override fun run(){
                val msg: Message = handler.obtainMessage()
                handler.sendMessage(msg)
                cancel()
            }

            override fun cancel(): Boolean {
                Log.d("TEST", "cancel: end")
                return super.cancel()
            }
        }
        timer.schedule(timerTask,20000)

        val inputMethodManager : InputMethodManager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        // 뒤로가기
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            inputMethodManager.hideSoftInputFromWindow(edit_address.windowToken, 0)
            inputMethodManager.hideSoftInputFromWindow(edit_deposit.windowToken, 0)
            inputMethodManager.hideSoftInputFromWindow(edit_detail.windowToken, 0)
            inputMethodManager.hideSoftInputFromWindow(edit_detailAddress.windowToken, 0)
            inputMethodManager.hideSoftInputFromWindow(edit_maintenance.windowToken, 0)
            inputMethodManager.hideSoftInputFromWindow(edit_rent.windowToken, 0)
            inputMethodManager.hideSoftInputFromWindow(edit_size.windowToken, 0)
            inputMethodManager.hideSoftInputFromWindow(edit_title.windowToken, 0)

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

        // EditText
        edit_deposit.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(!p0.isNullOrEmpty()){
                    deposit_ = p0.toString().toFloat()
                }
            }
        })
        edit_detailAddress.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(!p0.isNullOrEmpty()){
                    detailAddress_ = p0.toString()
                }
            }
        })
        edit_maintenance.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(!p0.isNullOrEmpty()) {
                    maintenance_ = p0.toString().toFloat()
                }
            }
        })
        edit_rent.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(!p0.isNullOrEmpty()) {
                    rent_ = p0.toString().toFloat()
                }
            }
        })
        edit_size.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(!p0.isNullOrEmpty()) {
                    size_ = p0.toString().toFloat()
                }
            }
        })
        edit_title.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(!p0.isNullOrEmpty()) {
                    title_ = p0.toString()
                }
            }
        })
        edit_detail.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(!p0.isNullOrEmpty()) {
                    detail_ = p0.toString()
                }
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
            getAddress = true
            Navigation.findNavController(requireActivity(), R.id.fragment_container).navigate(
                RegisterRoomSecondFragmentDirections.actionRegisterRoomSecondFragmentToAddrWebviewFragment()
            )
        }

        btn_cancel.setOnClickListener {
            Navigation.findNavController(requireActivity(), R.id.fragment_container).popBackStack()
        }

        /**
         * var deposit: Float = -1f
        var maintenance: Float = -1f
        var rent: Float = -1f
        var size: Float = -1f
        var detailAddress: String? = null
        var title: String? = null
        var detail: String? = null
         */

        btn_register.setOnClickListener {
            if (deposit_ > 0f && maintenance_ > 0f && rent_ > 0f && size_ > 0f &&
                    detailAddress_ != null && title_ != null && detail_ != null && address_ != null ){
                val attrs: RoomAttributes = RoomAttributes().apply{
                    registeredPerson = "test"
                    address = RegisterRoomSecondFragment.address_.toString()
                    detailAddress = detailAddress_
                    roomTitle = title_.toString()
                    deposit = deposit_
                    rent = rent_
                    roomSize = 30f

                    roomStructure = RoomAttributes.room1
                    maintenanceFee = maintenance_

                    isFullOption = checkbox_fullOptions.isChecked
                    parking = checkbox_parking.isChecked
                    washingMachine = checkbox_washingMachine.isChecked
                    refrigerator = checkbox_refrigerator.isChecked
                    airConditioner = checkbox_airConditioner.isChecked
                    closet = checkbox_closet.isChecked
                    deskChair = checkbox_deskChair.isChecked
                    gasRange = checkbox_gasRange.isChecked

                    waterFee = checkbox_water.isChecked
                    electronicFee = checkbox_elect.isChecked
                    internetFee = checkbox_internet.isChecked
                    gasFee = checkbox_gas.isChecked
                }
                attrs.saveDb()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (getAddress){
            if(address_ != null){
                val temp = address_?.split(" ")
                this.adminArea_ = temp?.get(0)
                this.subLocality_ = temp?.get(1)
                this.thoroughfare_ = temp?.get(2)
                this.subThoroughfare_ = temp?.get(3)
                val tempStr = String.format("%s %s %s %s", this.adminArea_, this.subLocality_, this.thoroughfare_, this.subThoroughfare_)
                address_ = tempStr
                edit_address.setText(tempStr.toCharArray(), 0, tempStr.toCharArray().size)
                if(temp?.get(4) != ""){
                    this.detailAddress_ = temp?.get(4)
                    edit_detailAddress.setText(this.detailAddress_?.toCharArray(), 0, this.detailAddress_?.toCharArray()?.size!!)
                }
                Log.d("TEST", "onResume: $temp")
            }
        }
    }
}