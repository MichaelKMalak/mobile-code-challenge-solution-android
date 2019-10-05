package io.door2door.mobile_code_challenge.mainScreen.features.mapFeature.model

import com.google.android.gms.maps.model.LatLng

data class StopLocationsModel(val pickupLocation: LatLng? = null,
                              val dropOffLocation: LatLng? = null,
                              val intermediateStopLocations: List<LatLng>)

