package io.door2door.mobile_code_challenge.mainScreen.features.mapFeature.presenter

import com.google.android.gms.maps.model.LatLng

interface MapPresenter {

  fun viewAttached()

  fun viewDetached()

  fun mapLoaded()
}