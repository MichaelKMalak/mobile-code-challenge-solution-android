package io.door2door.mobile_code_challenge.mainScreen.features.mapFeature.mapper

import com.google.android.gms.maps.model.LatLng
import io.door2door.mobile_code_challenge.data.events.*
import io.door2door.mobile_code_challenge.mainScreen.features.mapFeature.model.*
import javax.inject.Inject

class StopLocationsMapper @Inject constructor() : BaseBookingMapper<StopLocationsModel> {
    override fun mapDataModelToViewModel(dataModel: Event): StopLocationsModel {
        return when (dataModel) {
            is BookingOpened -> {
                val pickupLatLng =
                    LatLng(dataModel.data.pickupLocation.lat, dataModel.data.pickupLocation.lng)
                val dropOffLatLng =
                    LatLng(dataModel.data.dropoffLocation.lat, dataModel.data.dropoffLocation.lng)
                val intermediateStopsLatLng =
                    dataModel.data.intermediateStopLocations.map { LatLng(it.lat, it.lng) }
                StopLocationsModel(
                    BOOKING_OPENED,
                    pickupLatLng,
                    dropOffLatLng,
                    intermediateStopsLatLng
                )
            }
            is IntermediateStopLocationsChanged -> {
                val intermediateStopsLatLng = dataModel.data.map { LatLng(it.lat, it.lng) }
                StopLocationsModel(STOPS_UPDATED, null, null, intermediateStopsLatLng)
            }
            else -> StopLocationsModel(CLEAR, null, null, null)
        }
    }
}