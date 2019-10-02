package io.door2door.mobile_code_challenge.mainScreen.features.rideUpdates.view

interface RideUpdatesView {
    fun updateStatus(status: String, pickupAddress: String?, isBookingClosed: Boolean, dropoffAddress: String?)
}