package io.door2door.mobile_code_challenge.mainScreen.features.rideUpdates.view

interface RideUpdatesView {
    fun updateInfo(status: String, isBookingClosed: Boolean, pickupAddress: String?, dropoffAddress: String?)
}