package io.door2door.mobile_code_challenge.mainScreen.features.mapFeature.mapper

import com.google.android.gms.maps.model.LatLng
import io.door2door.mobile_code_challenge.data.events.BaseBookingMapper
import io.door2door.mobile_code_challenge.data.events.BookingOpened
import io.door2door.mobile_code_challenge.data.events.Event
import io.door2door.mobile_code_challenge.data.events.IntermediateStopLocationsChanged
import io.door2door.mobile_code_challenge.mainScreen.features.mapFeature.model.StopLocationsModel
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
            pickupLatLng = LatLng(dataModel.data.pickupLocation.lat, dataModel.data.pickupLocation.lng),
            dropOffLatLng = LatLng(
                dataModel.data.dropoffLocation.lat,
                dataModel.data.dropoffLocation.lng
            ),
            intermediateStopLatLng = dataModel.data.intermediateStopLocations.map { LatLng(it.lat, it.lng) }
        )
    }

    private fun getUpdatedStopLocations(dataModel: IntermediateStopLocationsChanged): StopLocationsModel {
        return StopLocationsModel(
            pickupLatLng = null,
            dropOffLatLng = null,
            intermediateStopLatLng = dataModel.data.map { LatLng(it.lat, it.lng) })
    }

    private fun getNoLocations() =
        StopLocationsModel(intermediateStopLatLng = listOf(LatLng(0.0, 0.0)))
}