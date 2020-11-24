package com.tristar.jjinbang.ui.search

import android.graphics.Color
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.tristar.jjinbang.R
import kotlinx.android.synthetic.main.maps_fragment.*
import kotlinx.android.synthetic.main.maps_fragment.view.*

class SearchFragment : Fragment(), OnMapReadyCallback {
    private var currentLatitude = 37.56
    private var currentLongitude = 126.97
    
    private var currentLocationName: String = "중앙대학교"

    private lateinit var mMap: GoogleMap
    
    private lateinit var geocoder: Geocoder

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.maps_fragment, container, false)
        view.mapView.getMapAsync(this)
        return view
    }
    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }
    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }
    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }
    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }
    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }
    override fun onDestroy() {
        super.onDestroy()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /** Set searchView's text color white **/
        val textView = searchView.findViewById<EditText>(androidx.appcompat.R.id.search_src_text)
        textView.setTextColor(Color.WHITE)

        setOptions.setOnClickListener {
            Navigation.findNavController(requireActivity(), R.id.fragment_container).navigate(
                SearchFragmentDirections.actionSearchFragmentToSearchOptionFragment()
            )
        }
        mapView.onCreate(savedInstanceState)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        geocoder = Geocoder(requireContext())

        val initLoc = getLatLng(currentLocationName)
        if(initLoc ==null){
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                LatLng(currentLatitude, currentLongitude), 15F))
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(initLoc, 15F))

        mMap.setOnCameraMoveListener{
            currentLatitude = mMap.cameraPosition.target.latitude
            currentLongitude = mMap.cameraPosition.target.longitude

            val addrNameList: List<Address> =
                geocoder.getFromLocation(currentLatitude, currentLongitude, 1)
            if(!addrNameList.isEmpty()){
                val addrName = addrNameList[0]
                val adminArea = addrName.adminArea // 도, 특별시, 광역시
                val locality = addrName.locality // 시 --> 특별시, 광역시 는 해당 x
                val subLocality = addrName.subLocality // 구 --> 없는 시, 군은 null
                val thoroughfare = addrName.thoroughfare // 동
                Log.d("TEST", "Location: $adminArea, $locality, $subLocality, $thoroughfare")
            }

        }
    }
    
    private fun getLatLng(locName: String): LatLng?{
        val addrList: List<Address> = geocoder.getFromLocationName(locName, 10)

        if(!addrList.isEmpty()){
            val addr: Address = addrList[0]
            val lat: Double = addr.latitude
            val lng: Double = addr.longitude
            return LatLng(lat, lng)
        }
        return null
    }

}