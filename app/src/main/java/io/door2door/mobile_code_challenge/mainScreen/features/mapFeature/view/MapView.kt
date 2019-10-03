package io.door2door.mobile_code_challenge.mainScreen.features.mapFeature.view

import com.google.android.gms.maps.model.LatLng

interface MapView {

    fun obtainGoogleMap()

    fun clearMap()

    fun loadVehicleLocation(finalLatLng: LatLng)

    //fun getBearing(): Float?
}