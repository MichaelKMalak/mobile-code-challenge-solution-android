package io.door2door.mobile_code_challenge.mainScreen.features.rideUpdates.view

interface RideUpdatesView {
    fun loadBookingStatus(status: String, isBookingClosed: Boolean, pickupAddress: String?, dropoffAddress: String?)
}