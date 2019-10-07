package io.door2door.mobile_code_challenge.mainScreen.features.rideUpdates.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import androidx.core.view.isVisible
import io.door2door.mobile_code_challenge.R
import io.door2door.mobile_code_challenge.mainScreen.features.rideUpdates.dagger.DaggerRideUpdatesComponent
import io.door2door.mobile_code_challenge.mainScreen.features.rideUpdates.dagger.RideUpdatesModule
import io.door2door.mobile_code_challenge.mainScreen.features.rideUpdates.presenter.RideUpdatesPresenter
import io.door2door.mobile_code_challenge.mainScreen.view.MainScreenActivity
import kotlinx.android.synthetic.main.feature_ride_updates.view.*
import javax.inject.Inject

class RideUpdatesLayout : RelativeLayout, RideUpdatesView {

  @Inject
  lateinit var rideUpdatesPresenter: RideUpdatesPresenter

  constructor(context: Context) : super(context) {
    setUp(context)
  }

  constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
    setUp(context)
  }

  constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
      context,
      attrs,
      defStyleAttr
  ) {
    setUp(context)
  }

  private fun setUp(context: Context) {
    LayoutInflater.from(context).inflate(R.layout.feature_ride_updates, this, true)
    injectDependencies()
  }

  private fun injectDependencies() {
    DaggerRideUpdatesComponent.builder()
        .mainScreenComponent((context as MainScreenActivity).mainScreenComponent)
        .rideUpdatesModule(RideUpdatesModule(this))
        .build()
        .injectRideUpdatesView(this)
  }

  override fun onAttachedToWindow() {
    super.onAttachedToWindow()
    rideUpdatesPresenter.viewAttached()
  }

    /**
     * Functions for displaying different types of addresses on screen
     */
    override fun updateStartEndAddresses(pickupAddress: String, dropOffAddress: String) {
        pickupLocationText.text = pickupAddress
        dropOffLocationText.text = dropOffAddress
    }



    override fun updateNextStopAddress(nextStopAddress: String) {
        nextStopLocationText.text = StringBuilder()
            .append(resources.getString(R.string.heading_to)).append(" ")
            .append(nextStopAddress)
    }

    /**
     * Functions for handling status updates
     */

    override fun updateStatus(status: String, bookingClosed: Boolean) {
        bookingIsClosedText.isVisible = bookingClosed
        bookingIsOpenText.isVisible = !bookingClosed
        rideStatusText.text = status
    }


    override fun toggleNextStopAddressVisibility(status: String) {
        if (status == resources.getString(R.string.in_vehicle))
            nextStopLocationText.isVisible = true

        if (status == resources.getString(R.string.ride_finished))
            nextStopLocationText.isVisible = false
    }
}