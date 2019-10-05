package io.door2door.mobile_code_challenge.network

import com.squareup.moshi.Moshi
import io.door2door.mobile_code_challenge.data.events.*
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.Subject
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import javax.inject.Inject

class BookingsWebSocketImp @Inject constructor(
    private val okHttpClient: OkHttpClient,
    moshi: Moshi) : BookingsWebSocket {

  private val statusUpdateSubject: Subject<Event> = BehaviorSubject.create()
  private val vehicleLocationUpdateSubject: Subject<Event> = BehaviorSubject.create()
  private val webSocketUrl = "wss://d2d-frontend-code-challenge.herokuapp.com"

  private val bookingWebSocketListener = BookingWebSocketListener(moshi)

  override fun connectToWebSocket() {
    val request = Request.Builder().url(webSocketUrl).build()
    okHttpClient.newWebSocket(request, bookingWebSocketListener)
  }

  override fun getStatusUpdates(): Observable<Event> = statusUpdateSubject.hide()

  override fun getVehicleLocationUpdates(): Observable<Event> = vehicleLocationUpdateSubject.hide()

  inner class BookingWebSocketListener(private val moshi: Moshi) : WebSocketListener() {

    override fun onMessage(webSocket: WebSocket, text: String) {
      super.onMessage(webSocket, text)
      moshi.adapter(Event::class.java).fromJson(text)?.let {
        when (it) {
          is VehicleLocationUpdated -> vehicleLocationUpdateSubject.onNext(it)
          else -> statusUpdateSubject.onNext(it)
        }
      }
    }
  }
}