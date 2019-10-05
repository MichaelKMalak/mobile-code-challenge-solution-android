package io.door2door.mobile_code_challenge.mainScreen.features.mapFeature.view

import com.google.android.gms.maps.model.LatLng

interface MapView {

    fun obtainGoogleMap()

    fun clearMap()

    fun updateVehicleLocation(finalLatLng: LatLng)

    fun showStartEndMarkers(pickupLatLng: LatLng,
                            dropOffLatLng: LatLng)

    fun updateStopsMarkers(intermediateStopLatLng: List<LatLng>)
}