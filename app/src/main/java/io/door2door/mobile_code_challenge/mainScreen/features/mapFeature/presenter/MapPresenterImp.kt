package io.door2door.mobile_code_challenge.mainScreen.features.mapFeature.presenter

import android.util.Log
import io.door2door.mobile_code_challenge.mainScreen.features.mapFeature.mapper.StatusLocationMapper
import io.door2door.mobile_code_challenge.mainScreen.features.mapFeature.mapper.VehicleLocationMapper
import io.door2door.mobile_code_challenge.mainScreen.features.mapFeature.model.BOOKING_CLOSED
import io.door2door.mobile_code_challenge.mainScreen.features.mapFeature.model.BOOKING_OPENED
import io.door2door.mobile_code_challenge.mainScreen.features.mapFeature.model.VehicleLocationModel
import io.door2door.mobile_code_challenge.mainScreen.features.mapFeature.view.MapView
import io.door2door.mobile_code_challenge.mainScreen.interactor.MainScreenInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MapPresenterImp @Inject constructor(private val mapView: MapView,
    private val mainScreenInteractor: MainScreenInteractor,
    private val vehicleLocationMapper: VehicleLocationMapper,
    private val statusLocationMapper: StatusLocationMapper) : MapPresenter {

    private val disposables = CompositeDisposable()
    private  val tag = MapPresenterImp::class.simpleName

    private var vehicleLocation: VehicleLocationModel? = null


  override fun viewAttached() {
    mapView.obtainGoogleMap()
      this.subscribeToVehicleLocationUpdates()
      //subscribeToStatusLocationUpdates()
  }

  override fun viewDetached() {
    disposables.dispose()
  }

  override fun mapLoaded() {
      vehicleLocation?.latLng?.let { mapView.updateMapView(it) }
  }

    //private fun subscribeToStatusLocationUpdates()
    //getVehicleLocationUpdates from mainScreenInteractor
    //getBookingStatusUpdates from mainScreenInteractor

    private fun subscribeToVehicleLocationUpdates() {
        disposables.add(mainScreenInteractor.getVehicleLocationUpdates(vehicleLocationMapper)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                vehicleLocation = it
                mapLoaded()
                //or make vehicleLocation shared to the MapView 3alatol
            }, {
                Log.d(tag, "Error on getting vehicle location updates")
            }))
    }
}