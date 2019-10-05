package io.door2door.mobile_code_challenge.mainScreen.features.rideUpdates.view

interface RideUpdatesView {
    fun updateAddresses(pickupAddress: String, dropOffAddress: String)
    fun updateStatus(status: String, bookingClosed: Boolean)
}