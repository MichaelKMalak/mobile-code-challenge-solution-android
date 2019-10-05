package io.door2door.mobile_code_challenge.mainScreen.features.mapFeature.model

import com.google.android.gms.maps.model.LatLng

data class StopLocationsModel(val pickupLatLng: LatLng? = null,
                              val dropOffLatLng: LatLng? = null,
                              val intermediateStopLatLng: List<LatLng>)

