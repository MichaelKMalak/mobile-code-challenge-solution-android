package io.door2door.mobile_code_challenge.data.events

import com.squareup.moshi.Json

enum class EventType { bookingOpened, bookingClosed, vehicleLocationUpdated, statusUpdated, intermediateStopLocationsChanged }

sealed class Event(@Json(name = "event") val eventType: EventType)

data class BookingOpened(val event: String, val data: Data) : Event(EventType.bookingOpened) {

    data class Data(
        val status: String,
        val vehicleLocation: Location,
        val pickupLocation: Location,
        val dropoffLocation: Location,
        val intermediateStopLocations: List<Location>
    )
}

data class BookingClosed(val event: String, val data: String?) : Event(EventType.bookingClosed)

data class StatusUpdated(val event: String, val data: String) : Event(EventType.statusUpdated)

data class VehicleLocationUpdated(val event: String, val data: Location) :
    Event(EventType.vehicleLocationUpdated)

data class IntermediateStopLocationsChanged(val event: String, val data: List<Location>) :
    Event(EventType.intermediateStopLocationsChanged)