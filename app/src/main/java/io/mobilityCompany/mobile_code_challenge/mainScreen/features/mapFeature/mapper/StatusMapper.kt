package io.mobilityCompany.mobile_code_challenge.mainScreen.features.mapFeature.mapper

import io.mobilityCompany.mobile_code_challenge.data.events.BaseBookingMapper
import io.mobilityCompany.mobile_code_challenge.data.events.BookingClosed
import io.mobilityCompany.mobile_code_challenge.data.events.BookingOpened
import io.mobilityCompany.mobile_code_challenge.data.events.Event
import io.mobilityCompany.mobile_code_challenge.mainScreen.features.mapFeature.model.BOOKING_CLOSED
import io.mobilityCompany.mobile_code_challenge.mainScreen.features.mapFeature.model.BOOKING_OPENED
import io.mobilityCompany.mobile_code_challenge.mainScreen.features.mapFeature.model.CLEAR
import io.mobilityCompany.mobile_code_challenge.mainScreen.features.mapFeature.model.StatusModel
import javax.inject.Inject

class StatusMapper @Inject constructor() : BaseBookingMapper<StatusModel> {
    override fun mapDataModelToViewModel(dataModel: Event): StatusModel {
        return when (dataModel) {
            is BookingOpened -> StatusModel(BOOKING_OPENED)
            is BookingClosed -> StatusModel(BOOKING_CLOSED)
            else -> StatusModel(CLEAR)
        }
    }
}