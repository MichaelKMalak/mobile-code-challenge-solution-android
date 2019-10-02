package io.door2door.mobile_code_challenge.mainScreen.interactor

import io.door2door.mobile_code_challenge.data.events.BaseBookingMapper
import io.door2door.mobile_code_challenge.network.BookingsWebSocket
import io.reactivex.Observable
import javax.inject.Inject

class MainScreenInteractorImp @Inject constructor(
    private val bookingsWebSocket: BookingsWebSocket) :
    MainScreenInteractor {

  override fun <V> getVehicleLocationUpdates(
      bookingLocationMapper: BaseBookingMapper<V>): Observable<V> {
    return bookingsWebSocket.getVehicleLocationUpdates()
        .map { bookingLocationMapper.mapDataModelToViewModel(it) }
  }

  override fun <V> getBookingStatusUpdates(
      bookingStatusMapper: BaseBookingMapper<V>): Observable<V> {
    return bookingsWebSocket.getStatusUpdates()
        .map { bookingStatusMapper.mapDataModelToViewModel(it) }
  }

  override fun connectToWebSocket() {
    bookingsWebSocket.connectToWebSocket()
  }
}