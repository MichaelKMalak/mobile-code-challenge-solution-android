package io.mobilityCompany.mobile_code_challenge.mainScreen.features.mapFeature.presenter

import android.util.Log
import io.mobilityCompany.mobile_code_challenge.mainScreen.features.mapFeature.mapper.StatusMapper
import io.mobilityCompany.mobile_code_challenge.mainScreen.features.mapFeature.mapper.StopLocationsMapper
import io.mobilityCompany.mobile_code_challenge.mainScreen.features.mapFeature.mapper.VehicleLocationMapper
import io.mobilityCompany.mobile_code_challenge.mainScreen.features.mapFeature.model.BOOKING_CLOSED
import io.mobilityCompany.mobile_code_challenge.mainScreen.features.mapFeature.view.MapView
import io.mobilityCompany.mobile_code_challenge.mainScreen.interactor.MainScreenInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MapPresenterImp @Inject constructor(
    private val mapView: MapView,
    private val mainScreenInteractor: MainScreenInteractor,
    private val vehicleLocationMapper: VehicleLocationMapper,
    private val stopLocationsMapper: StopLocationsMapper,
    private val statusMapper: StatusMapper
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
        subscribeToWebSocketUpdates()
    }

    private fun subscribeToWebSocketUpdates() {
        this.subscribeToVehicleLocationUpdates()
        this.subscribeToStopLocationsUpdates()
        this.subscribeToStatusUpdates()
    }

    private fun subscribeToVehicleLocationUpdates() {
        disposables.add(mainScreenInteractor.getVehicleLocationUpdates(vehicleLocationMapper)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                mapView.updateVehicleMarker(it.latLng)
            }, {
                Log.d(tag, "Error on getting vehicle location updates")
            })
        )
    }

    private fun subscribeToStopLocationsUpdates() {
        disposables.add(mainScreenInteractor.getStopLocationsUpdates(stopLocationsMapper)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.pickupLatLng != null && it.dropOffLatLng != null) {
                    mapView.showStartEndMarkers(
                        it.pickupLatLng,
                        it.dropOffLatLng
                    )
                }
                mapView.updateStopsMarkers(it.intermediateStopLatLng)
            }, {
                Log.d(tag, "Error on getting vehicle location updates")
            })
        )
    }

    private fun subscribeToStatusUpdates() {
        disposables.add(
            mainScreenInteractor.getBookingStatusUpdates(statusMapper)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.status == BOOKING_CLOSED)
                        mapView.clearAllMarkers()
                }, {
                    Log.d(tag, "Error on getting vehicle location updates")
                })
        )
    }
}