package io.door2door.mobile_code_challenge.mainScreen.features.mapFeature.mapper

import com.google.android.gms.maps.model.LatLng
import io.door2door.mobile_code_challenge.data.events.BaseBookingMapper
import io.door2door.mobile_code_challenge.data.events.BookingClosed
import io.door2door.mobile_code_challenge.data.events.BookingOpened
import io.door2door.mobile_code_challenge.data.events.Event
import io.door2door.mobile_code_challenge.mainScreen.features.mapFeature.model.BOOKING_CLOSED
import io.door2door.mobile_code_challenge.mainScreen.features.mapFeature.model.BOOKING_OPENED
import io.door2door.mobile_code_challenge.mainScreen.features.mapFeature.model.CLEAR
import io.door2door.mobile_code_challenge.mainScreen.features.mapFeature.model.StatusLocationModel
import javax.inject.Inject

class StatusLocationMapper @Inject constructor() : BaseBookingMapper<StatusLocationModel> {
    override fun mapDataModelToViewModel(dataModel: Event): StatusLocationModel {
        return when (dataModel) {
            is BookingOpened -> StatusLocationModel(
                BOOKING_OPENED,
                LatLng(dataModel.data.vehicleLocation.lat, dataModel.data.vehicleLocation.lng)
            )
            is BookingClosed -> StatusLocationModel(BOOKING_CLOSED)
            else -> StatusLocationModel(CLEAR)
        }
    }
}