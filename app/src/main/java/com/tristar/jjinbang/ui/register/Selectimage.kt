package com.tristar.jjinbang.ui.register

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.tristar.jjinbang.R
import kotlinx.android.synthetic.main.selectimage.*
import org.json.JSONObject
import java.io.ByteArrayOutputStream

import android.content.Context

import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.util.Base64
import androidx.core.graphics.drawable.toBitmap
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest

import okhttp3.ResponseBody
import retrofit2.Call


import java.io.InputStream
import kotlin.collections.HashMap

class Selectimage : Fragment() {
    companion object {
        fun newInstance() = Selectimage()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.selectimage, container, false)
    }

    private var imageview: ImageView? = null
    private var imageData: ByteArray? = null
    private val postURL: String = "http://218.155.251.115:5000/api/postname"
    val REQUEST_GALLERY_TAKE = 2
    var imageBytes = byteArrayOf()
    lateinit var encodedImage : String
    lateinit var requestQueue: RequestQueue
    lateinit var bitmap: Bitmap
    lateinit var filePath : Uri
    lateinit var inputStream : InputStream
    val cond: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        SelectImage_sendimage.setOnClickListener {postRequest()}
        SelectImage_imageView.setOnClickListener{openGalleryForImage()}


    }

    private fun openGalleryForImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_GALLERY_TAKE)


    }




    private fun postRequest() {

        if (cond == 3) {
            val bitmap: Bitmap? = imageview?.drawable?.toBitmap()

            val byteArrayOutputStream = ByteArrayOutputStream()
            if (bitmap != null) {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
            }
            val byteArray: ByteArray = byteArrayOutputStream.toByteArray()

            val convertImage: String = Base64.encodeToString(byteArray, Base64.DEFAULT)

            Log.e("CONVERT IMAGE : ", "" + convertImage)

            val name: String = ""
            val paramObject = JSONObject()
            paramObject.put("file", "data:image/png;base64,$convertImage")
            paramObject.put("userName", name)

            print(paramObject)

            //API.service.postName(paramObject).enqueue(object : Callback<ResponseBody> {
            /*fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                println("POST Throwable EXCEPTION:: " + t.message)
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
            }
            */
            /*fun onResponse(
                //call: Call<ResponseBody>,
                response: Response<ResponseBody>
            ) {
                if (response.isSuccess) {
                    val msg = response
                    println("POST msg from server :: " + msg)
                    //Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                }
            }*/


            /*apiInteface.getTest(body, xList, yList).enqueue(object : Callback<ResponseBody> {
                fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Log.d("AAA", "FAIL REQUEST ==> " + t.localizedMessage)
                    //drawImageView.clear()
                }

                fun onResponse(
                    //call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    Log.d("AAA", "REQUEST SUCCESS ==> ")
                    val file = response.result?.byteStream()
                    val bitmap = BitmapFactory.decodeStream(file)
                    //drawImageView.clear()
                }
            })*/

            //API.service.postName(paramObject).enqueue(object : Callback<ResponseBody> {
            fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                println("POST Throwable EXCEPTION:: " + t.message)
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
            }

            fun onResponse(
                //call: Call<ResponseBody>,
                response: Response<ResponseBody>
            ) {
                if (response.isSuccess) {
                    //val file = response.body()?.byteStream()
                }
            }


            val stringRequest = object : StringRequest(Request.Method.POST, postURL,
                Response.Listener<String> { response ->
                    Toast.makeText(context, response, Toast.LENGTH_LONG).show()
                }, Response.ErrorListener { error ->
                    Toast.makeText(context, "Error: ${error.toString()}", Toast.LENGTH_LONG).show()
                }) {
                override fun getParams(): HashMap<String, String> {
                    val params = HashMap<String, String>()
                    //val imageData = imageToString(bitmap)
                    //params.put("image", imageData)
                    return params
                }
            }
            //requestQueue.add(stringRequest)







        }
    }


    fun selectImage() {
        val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
            type = "image/*"
        }
        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            this.startActivityForResult(intent, REQUEST_GALLERY_TAKE)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            2 -> {
                if (data != null) {
                    filePath = data.data!!
                }
                inputStream = context?.contentResolver?.openInputStream(filePath)!!
                bitmap = BitmapFactory.decodeStream(inputStream)
                if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_GALLERY_TAKE) {
                    val uri = data?.data
                    if(uri != null) {
                        SelectImage_imageView.setImageURI(uri)
                    }
                }
                Toast.makeText(context, "${filePath.toString()}", Toast.LENGTH_LONG).show()
            }
        }
    }
}

