package com.tristar.jjinbang.ui.register

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.tristar.jjinbang.R
import kotlinx.android.synthetic.main.register_room_first.*
import kotlinx.coroutines.GlobalScope
import java.net.URI

class RegisterRoomFirstFragment : Fragment() {

    private val REQUEST_CAMERA = 100
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.register_room_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_capture.setOnClickListener {
            dispatchTakePictureIntent()
            isCameraCaptured = true
        }

        btn_next.setOnClickListener {
            // 사진 체크하고
            Navigation.findNavController(requireActivity(), R.id.fragment_container).navigate(
                RegisterRoomFirstFragmentDirections.actionRegisterRoomFirstFragmentToRegisterRoomSecondFragment()
            )
        }

    }

    override fun onResume() {
        super.onResume()
        if(isCameraCaptured){
            selectImage()
            isCameraCaptured = false
        }
    }

    private fun dispatchTakePictureIntent(){
        Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA).also { takePictureIntent ->
            takePictureIntent.resolveActivity(requireActivity().packageManager)?.also{
                startActivityForResult(takePictureIntent, REQUEST_CAMERA)
            }
        }


    }

    val REQUEST_IMAGE_GET = 1

    fun selectImage() {
        val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
            type = "image/*"
        }
        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            this.startActivityForResult(intent, REQUEST_IMAGE_GET)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_CAMERA && resultCode == RESULT_OK){
            val imageBitmap = data?.extras?.get("data") as Bitmap
            view_camera.setImageBitmap(imageBitmap)
        }
        if(requestCode == REQUEST_IMAGE_GET && resultCode == RESULT_OK){
            val imgUri : Uri = data?.data as Uri
            val imgBitmap : Bitmap? = getBitmap(requireContext(), imgUri)
            view_camera.setImageBitmap(imgBitmap)
            Log.d("TEST", "onActivityResult: $imgUri")
            Log.d("TEST", "onActivityResult: $imgBitmap")
        }
    }

    fun getBitmap(context: Context, imageUri: Uri): Bitmap ? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            ImageDecoder.decodeBitmap(
                ImageDecoder.createSource(
                    context.contentResolver,
                    imageUri))
        } else {
            context.contentResolver.openInputStream(imageUri) ?.use {
                    inputStream ->
                BitmapFactory.decodeStream(inputStream)
            }
        }
    }


    companion object{
        private var isCameraCaptured = false
    }
}