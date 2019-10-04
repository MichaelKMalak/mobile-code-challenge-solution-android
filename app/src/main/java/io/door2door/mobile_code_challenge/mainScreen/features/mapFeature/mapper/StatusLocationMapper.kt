package io.door2door.mobile_code_challenge.mainScreen.features.mapFeature.mapper

import com.google.android.gms.maps.model.LatLng
import io.door2door.mobile_code_challenge.data.events.*
import io.door2door.mobile_code_challenge.mainScreen.features.mapFeature.model.StatusLocationModel
import javax.inject.Inject

class StatusLocationMapper @Inject constructor() : BaseBookingMapper<StatusLocationModel> {
  override fun mapDataModelToViewModel(dataModel: Event): StatusLocationModel {
    return when (dataModel) {
      is BookingOpened -> StatusLocationModel(true,
          LatLng(dataModel.data.pickupLocation.lat, dataModel.data.pickupLocation.lng),
              LatLng(dataModel.data.dropoffLocation.lat, dataModel.data.dropoffLocation.lng),
              dataModel.data.intermediateStopLocations.map{LatLng(it.lat, it.lng)}
      )
      is BookingClosed -> StatusLocationModel(false)
      is IntermediateStopLocationsChanged -> StatusLocationModel(true,
        null,null,
        dataModel.data.map{LatLng(it.lat, it.lng)})
      else -> StatusLocationModel(true)
        }
    }
  }
