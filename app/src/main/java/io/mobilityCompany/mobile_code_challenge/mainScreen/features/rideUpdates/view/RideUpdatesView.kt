package io.mobilityCompany.mobile_code_challenge.mainScreen.features.rideUpdates.view

interface RideUpdatesView {
    fun updateStartEndAddresses(pickupAddress: String, dropOffAddress: String)
    fun updateStatus(status: String, bookingClosed: Boolean)
    fun updateNextStopAddress(nextStopAddress: String)
    fun toggleNextStopAddressVisibility(status: String)
}