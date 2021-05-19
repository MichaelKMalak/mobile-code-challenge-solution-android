package io.mobilityCompany.mobile_code_challenge.network

import io.mobilityCompany.mobile_code_challenge.data.events.Event
import io.reactivex.Observable

interface BookingsWebSocket {

  fun connectToWebSocket()

  fun getStatusUpdates(): Observable<Event>

  fun getVehicleLocationUpdates(): Observable<Event>

  fun getStopLocationUpdates(): Observable<Event>
}