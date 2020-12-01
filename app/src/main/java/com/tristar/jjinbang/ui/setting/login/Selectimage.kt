package com.tristar.jjinbang.ui.setting.login

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.tristar.jjinbang.R
import kotlinx.android.synthetic.main.selectimage.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream


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

    private lateinit var inputId: String
    private lateinit var inputPassword: String
    private var imageview: ImageView? = null
    val REQUEST_GALLERY_TAKE = 2
    var imagepath = ""

    private val url = "http://" + "10.0.2.2" + ":" + 5000 + "/"
    private var postBodyString: String? = null
    private var mediaType: MediaType? = null
    private var requestBody: RequestBody? = null
    private val connect: Button? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        SelectImage_sendimage.setOnClickListener { postRequest() }
        SelectImage_imageView.setOnClickListener{openGalleryForImage()}
    }

    private fun openGalleryForImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_GALLERY_TAKE)
    }

    private fun postRequest(){
        """val name = "Test"
        val surname = "First"

        val jsonObj = JsonObject()
        jsonObj.addProperty("name",name.toString())
        jsonObj.addProperty("surname",surname.toString())

        API.service.postName(jsonObj).enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                println("POST Throwable EXCEPTION:: " + t.message)
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
            }
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    val msg = response.body()?.string()
                    println("POST msg from server :: " + msg)
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                }
            }
        })"""
        val stream = ByteArrayOutputStream()
        val options = BitmapFactory.Options()
        options.inPreferredConfig = Bitmap.Config.RGB_565

        // Read BitMap by file path
        val bitmap = BitmapFactory.decodeFile(imagepath, options)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
        val byteArray: ByteArray = stream.toByteArray()

        val postBodyImage: RequestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart(
                "image",
                "androidFlask.jpg",
                RequestBody.create(MediaType.parse("image/*jpg"), byteArray)
            )
            .build()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            2 -> {
                if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_GALLERY_TAKE) {
                    SelectImage_imageView.setImageURI(data?.data)// handle chosen image
                    imagepath = data?.data.toString()
                }
            }
        }
    }
}

