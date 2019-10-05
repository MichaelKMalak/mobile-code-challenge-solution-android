package io.door2door.mobile_code_challenge.mainScreen.features.mapFeature.view

import com.google.android.gms.maps.model.LatLng

interface MapView {

    fun obtainGoogleMap()

    fun clearMap()

    fun showVehicleLocation(finalLatLng: LatLng)

    fun showStopsLocation (pickupLatLng: LatLng?,
                           dropOffLatLng: LatLng?,
                           intermediateStopLatLng: List<LatLng>)
}