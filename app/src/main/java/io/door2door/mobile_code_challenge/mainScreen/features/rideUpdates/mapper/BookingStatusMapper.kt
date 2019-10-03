package io.door2door.mobile_code_challenge.mainScreen.features.rideUpdates.mapper

import android.content.res.Resources
import io.door2door.mobile_code_challenge.R
import io.door2door.mobile_code_challenge.data.events.*
import io.door2door.mobile_code_challenge.mainScreen.features.rideUpdates.model.BookingStatusModel
import javax.inject.Inject

class BookingStatusMapper @Inject constructor(
    private val resources: Resources
) : BaseBookingMapper<BookingStatusModel> {

    override fun mapDataModelToViewModel(dataModel: Event): BookingStatusModel {
        return when (dataModel) {
            is BookingOpened -> getBookingOpenedModel(dataModel)
            is StatusUpdated -> getStatusUpdatedModel()
            is BookingClosed -> getBookingClosedModel()
            else -> getNoBookingModel()
        }
    }

    private fun getBookingOpenedModel(bookingOpened: BookingOpened) =
        BookingStatusModel(
            status = resources.getString(R.string.waiting_for_pickup),
            pickupAddress = bookingOpened.data.pickupLocation.address,
            dropoffAddress = bookingOpened.data.dropoffLocation.address
        )

    private fun getStatusUpdatedModel() =
        BookingStatusModel(status = resources.getString(R.string.in_vehicle))

    private fun getBookingClosedModel() =
        BookingStatusModel(
            status = resources.getString(R.string.ride_finished),
            isBookingClosed = true
        )

    private fun getNoBookingModel() =
        BookingStatusModel(status = resources.getString(R.string.no_booking))
}