package io.door2door.mobile_code_challenge.mainScreen.features.mapFeature.presenter

import android.util.Log
import io.door2door.mobile_code_challenge.mainScreen.features.mapFeature.mapper.StopLocationsMapper
import io.door2door.mobile_code_challenge.mainScreen.features.mapFeature.mapper.VehicleLocationMapper
import io.door2door.mobile_code_challenge.mainScreen.features.mapFeature.view.MapView
import io.door2door.mobile_code_challenge.mainScreen.interactor.MainScreenInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MapPresenterImp @Inject constructor(
    private val mapView: MapView,
    private val mainScreenInteractor: MainScreenInteractor,
    private val vehicleLocationMapper: VehicleLocationMapper,
    private val stopLocationsMapper: StopLocationsMapper
) : MapPresenter {

    private val disposables = CompositeDisposable()
    private val tag = MapPresenterImp::class.simpleName


    override fun viewAttached() {
        mapView.obtainGoogleMap()
    }

    override fun viewDetached() {
        disposables.dispose()
    }

    override fun mapLoaded() {
        this.subscribeToVehicleLocationUpdates()
        this.subscribeToStopLocationsUpdates()
    }

    private fun subscribeToVehicleLocationUpdates() {
        disposables.add(mainScreenInteractor.getVehicleLocationUpdates(vehicleLocationMapper)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                mapView.updateVehicleLocation(it.latLng)
                //  mainScreenInteractor.bearing = mapView.getBearing()
            }, {
                Log.d(tag, "Error on getting vehicle location updates")
            })
        )
    }
    private fun subscribeToStopLocationsUpdates() {
        disposables.add(mainScreenInteractor.getBookingStatusUpdates(stopLocationsMapper)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.status.isNotEmpty() && !it.intermediateStopLocations.isNullOrEmpty()) {
                    mapView.loadLocationMarkers(
                        it.pickupLocation, it.dropOffLocation,
                        it.intermediateStopLocations
                    )}
            }, {
                Log.d(tag, "Error on getting vehicle location updates")
            })
        )
    }
}