package io.door2door.mobile_code_challenge.mainScreen.features.rideUpdates.view

interface RideUpdatesView {
    fun showBookingStatus(status: String,
                          isBookingClosed: Boolean,
                          pickupAddress: String?,
                          dropOffAddress: String?)
}