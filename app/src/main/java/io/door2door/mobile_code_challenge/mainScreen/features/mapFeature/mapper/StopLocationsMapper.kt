package io.door2door.mobile_code_challenge.mainScreen.features.mapFeature.mapper

import com.google.android.gms.maps.model.LatLng
import io.door2door.mobile_code_challenge.data.events.*
import io.door2door.mobile_code_challenge.mainScreen.features.mapFeature.model.*
import javax.inject.Inject

class StopLocationsMapper @Inject constructor() : BaseBookingMapper<StopLocationsModel> {
    override fun mapDataModelToViewModel(dataModel: Event): StopLocationsModel {
        return when (dataModel) {
            is BookingOpened -> getInitialLocations(dataModel)
            is IntermediateStopLocationsChanged -> getUpdatedStopLocations(dataModel)
            else -> getNoLocations()
        }
    }

    private fun getInitialLocations(dataModel: BookingOpened): StopLocationsModel {
        return StopLocationsModel(
            pickupLocation = LatLng(dataModel.data.pickupLocation.lat, dataModel.data.pickupLocation.lng),
            dropOffLocation = LatLng(dataModel.data.dropoffLocation.lat, dataModel.data.dropoffLocation.lng),
            intermediateStopLocations = dataModel.data.intermediateStopLocations.map { LatLng(it.lat, it.lng) }
        )
    }

    private fun getUpdatedStopLocations(dataModel: IntermediateStopLocationsChanged): StopLocationsModel {
        return StopLocationsModel(
            pickupLocation = null,
            dropOffLocation = null,
            intermediateStopLocations = dataModel.data.map { LatLng(it.lat, it.lng) })
    }

    private fun getNoLocations() =
        StopLocationsModel(intermediateStopLocations = listOf(LatLng(0.0, 0.0)))
}