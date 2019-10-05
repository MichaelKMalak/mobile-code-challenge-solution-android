package io.door2door.mobile_code_challenge.mainScreen.interactor

import io.door2door.mobile_code_challenge.data.events.BaseBookingMapper
import io.door2door.mobile_code_challenge.mainScreen.features.mapFeature.model.VehicleLocationModel
import io.door2door.mobile_code_challenge.mainScreen.features.rideUpdates.model.BookingStatusModel
import io.reactivex.Observable

interface MainScreenInteractor {
//  var bearing<Float>: Observable

    fun connectToWebSocket()

    fun <V> getVehicleLocationUpdates(
      bookingLocationMapper: BaseBookingMapper<V>): Observable<V>

    fun <V> getBookingStatusUpdates(
      bookingStatusMapper: BaseBookingMapper<V>): Observable<V>

    fun <V> getStopLocationsUpdates(
        stopLocationsMapper: BaseBookingMapper<V>): Observable<V>
}