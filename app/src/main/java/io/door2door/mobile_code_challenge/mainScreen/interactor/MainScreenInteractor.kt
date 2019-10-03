package io.door2door.mobile_code_challenge.mainScreen.interactor

import io.door2door.mobile_code_challenge.data.events.BaseBookingMapper
import io.reactivex.Observable

interface MainScreenInteractor {
    fun connectToWebSocket()

    fun <V> getVehicleLocationUpdates(
        bookingLocationMapper: BaseBookingMapper<V>
    ): Observable<V>

    fun <V> getBookingStatusUpdates(
        bookingStatusMapper: BaseBookingMapper<V>
    ): Observable<V>
}