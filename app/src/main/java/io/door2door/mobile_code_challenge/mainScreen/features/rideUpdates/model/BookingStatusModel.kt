package io.door2door.mobile_code_challenge.mainScreen.features.rideUpdates.model

data class BookingStatusModel(val status: String,
                              val pickupAddress: String? = null,
                              val dropOffAddress: String? = null,
                              val isBookingClosed: Boolean = false)