package io.door2door.mobile_code_challenge.mainScreen.features.mapFeature.view

import android.animation.ObjectAnimator
import android.animation.TypeEvaluator
import android.content.Context
import android.location.Location
import android.util.AttributeSet
import android.util.Property
import android.view.LayoutInflater
import android.view.animation.LinearInterpolator
import android.widget.RelativeLayout
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import io.door2door.mobile_code_challenge.R
import io.door2door.mobile_code_challenge.mainScreen.features.mapFeature.dagger.DaggerMapComponent
import io.door2door.mobile_code_challenge.mainScreen.features.mapFeature.dagger.MapModule
import io.door2door.mobile_code_challenge.mainScreen.features.mapFeature.presenter.MapPresenter
import io.door2door.mobile_code_challenge.mainScreen.view.MainScreenActivity
import kotlinx.android.synthetic.main.feature_map.view.*
import javax.inject.Inject

private const val MARKER_ANIMATION_DURATION = 1000L
private const val VEHICLE_MARKER_ANCHOR = 0.5f
private const val PICKUP_DRAWABLE = R.drawable.pickup_location
private const val DROPOFF_DRAWABLE = R.drawable.drop_location
private const val STOPS_DRAWABLE = R.drawable.stop_location
private const val VEHICLE_DRAWABLE = R.drawable.marker_vehicle

class MapLayout : MapView, RelativeLayout {

    @Inject
    lateinit var mapPresenter: MapPresenter

    private var googleMap: GoogleMap? = null
    private var markerPositionAnimator: ObjectAnimator? = null
    private var markerRotationAnimator: ObjectAnimator? = null
    private var vehicleMarker: Marker? = null
    private var pickupMarker: Marker? = null
    private var dropOffMarker: Marker? = null
    private var intermediateStopsMarkers = mutableMapOf<LatLng, Marker?>()


    constructor(context: Context) : super(context) {
        setUp(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        setUp(context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context, attrs,
        defStyleAttr
    ) {
        setUp(context)
    }

    private fun setUp(context: Context) {
        LayoutInflater.from(context).inflate(R.layout.feature_map, this, true)
        injectDependencies()
        mapPresenter.viewAttached()
    }

    private fun injectDependencies() {
        DaggerMapComponent.builder()
            .mainScreenComponent((context as MainScreenActivity).mainScreenComponent)
            .mapModule(MapModule(this))
            .build()
            .injectMapView(this)
    }

    override fun obtainGoogleMap() {
        mapView.getMapAsync {
            googleMap = it
            mapPresenter.mapLoaded()
        }
    }

    override fun clearMap() {
        clearAllMarkers()
        googleMap?.clear()
    }

    /**
     *    Functions for displaying the vehicle and animating it
     */

    override fun updateVehicleMarker(finalLatLng: LatLng) {
        if (vehicleMarker == null) initializeVehicleMarker(finalLatLng)

        animateMarker(vehicleMarker, finalLatLng)
    }

    private fun initializeVehicleMarker(finalLatLng: LatLng) {
        showVehicleMarker(finalLatLng)
        moveCamera(finalLatLng)
    }

    private fun showVehicleMarker(finalLatLng: LatLng) {
        vehicleMarker = googleMap?.addMarker(
            MarkerOptions()
                .position(finalLatLng)
                .anchor(VEHICLE_MARKER_ANCHOR, VEHICLE_MARKER_ANCHOR)
        )
        vehicleMarker!!.setIcon(BitmapDescriptorFactory.fromResource(VEHICLE_DRAWABLE))
    }

    private fun moveCamera(finalLatLng: LatLng) {
        googleMap?.moveCamera(CameraUpdateFactory.newLatLng(finalLatLng))
    }

    private fun animateMarker(marker: Marker?, finalLatLng: LatLng) {
        marker?.let {
            if (marker.position != null) {
                animateMarkerPosition(marker, finalLatLng)
                animateMarkerRotation(marker, finalLatLng)
            } else {
                marker.position = finalLatLng
            }
        }
    }

    private fun animateMarkerPosition(marker: Marker, finalLatLng: LatLng) {
        val interpolator = LinearInterpolator()
        val typeEvaluator = TypeEvaluator<LatLng> { fraction, fromLatLng, toLatLng ->
            val interpolation = interpolator.getInterpolation(fraction)
            val lat =
                (toLatLng.latitude - fromLatLng.latitude) * interpolation + fromLatLng.latitude
            val lng =
                (toLatLng.longitude - fromLatLng.longitude) * interpolation + fromLatLng.longitude
            return@TypeEvaluator LatLng(lat, lng)
        }
        val property = Property.of(Marker::class.java, LatLng::class.java, "position")
        markerPositionAnimator?.cancel()
        markerPositionAnimator =
            ObjectAnimator.ofObject(marker, property, typeEvaluator, finalLatLng)
        markerPositionAnimator?.duration = MARKER_ANIMATION_DURATION
        markerPositionAnimator?.start()
    }

    private fun animateMarkerRotation(marker: Marker, finalLatLng: LatLng) {
        val initialLocation = marker.position.convertToLocation()
        val finalLocation = finalLatLng.convertToLocation()
        val newRotation = initialLocation?.bearingTo(finalLocation)
        newRotation?.let {
            val rotationProperty = Property.of(Marker::class.java, Float::class.java, "rotation")
            markerRotationAnimator?.cancel()
            markerRotationAnimator = ObjectAnimator.ofFloat(marker, rotationProperty, it)
            markerRotationAnimator?.duration = MARKER_ANIMATION_DURATION
            markerRotationAnimator?.start()
        }
    }

    private fun LatLng?.convertToLocation(): Location? = this?.let {
        val location = Location("")
        location.latitude = latitude
        location.longitude = longitude
        return location
    }

    /**
     * Functions for displaying markers at the different types of location stops
     */

    override fun showStartEndMarkers(pickupLatLng: LatLng,
                                     dropOffLatLng: LatLng) {
        pickupMarker = showMarkerWithDrawable(pickupLatLng, PICKUP_DRAWABLE)
        dropOffMarker = showMarkerWithDrawable(dropOffLatLng, DROPOFF_DRAWABLE)
    }

    override fun updateStopsMarkers(intermediateStopLatLng: List<LatLng>) {
        intermediateStopLatLng.forEach {
            var newMarker = intermediateStopsMarkers[it]
            if (newMarker == null) {
                newMarker = showMarkerWithDrawable(it, STOPS_DRAWABLE)
                intermediateStopsMarkers[it] = newMarker
            }
        }
    }

    private fun showMarkerWithDrawable(latLng: LatLng, drawable: Int): Marker? {
        val marker: Marker? = googleMap?.addMarker(
            MarkerOptions()
                .position(latLng)
        )
        marker?.setIcon(BitmapDescriptorFactory.fromResource(drawable))
        return marker
    }

    /**
     * Function for cleaning all displayed markers
     */

    override fun clearAllMarkers() {
        pickupMarker?.remove()
        dropOffMarker?.remove()
        vehicleMarker?.remove()

        intermediateStopsMarkers.forEach { (latLng, _) ->
            intermediateStopsMarkers[latLng]?.remove()
        }
        intermediateStopsMarkers.clear()
    }
}
