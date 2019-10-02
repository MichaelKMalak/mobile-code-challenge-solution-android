package io.door2door.mobile_code_challenge.mainScreen.features.mapFeature.mapper

import com.google.android.gms.maps.model.LatLng
import io.door2door.mobile_code_challenge.data.events.BaseBookingMapper
import io.door2door.mobile_code_challenge.data.events.Event
import io.door2door.mobile_code_challenge.data.events.VehicleLocationUpdated
import io.door2door.mobile_code_challenge.mainScreen.features.mapFeature.model.VehicleLocationModel
import javax.inject.Inject

class VehicleLocationMapper @Inject constructor() : BaseBookingMapper<VehicleLocationModel> {

  override fun mapDataModelToViewModel(dataModel: Event): VehicleLocationModel {
    val vehicleLocationUpdated = dataModel as VehicleLocationUpdated
    val currentLat = vehicleLocationUpdated.data.lat
    val currentLng = vehicleLocationUpdated.data.lng
    return VehicleLocationModel(LatLng(currentLat, currentLng))
  }
}