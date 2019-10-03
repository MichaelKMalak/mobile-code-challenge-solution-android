package io.door2door.mobile_code_challenge.data.events

enum class EventType { bookingOpened, bookingClosed, vehicleLocationUpdated, statusUpdated, intermediateStopLocationsChanged }

sealed class Event

data class BookingOpened(val event: String, val data: Data) : Event() {

    data class Data(
        val status: String,
        val vehicleLocation: Location,
        val pickupLocation: Location,
        val dropoffLocation: Location,
        val intermediateStopLocations: List<Location>
    )
}

data class BookingClosed(val event: String, val data: String?) : Event()

data class StatusUpdated(val event: String, val data: String) : Event()

data class VehicleLocationUpdated(val event: String, val data: Location) :
    Event()

data class IntermediateStopLocationsChanged(val event: String, val data: List<Location>) :
    Event()