package io.door2door.mobile_code_challenge.mainScreen.features.rideUpdates.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
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
    override fun updateStatus(status: String, pickupAddress: String?, isBookingClosed: Boolean, dropoffAddress: String?) {
       val bookingStatus = if (isBookingClosed) "Booking: Closed." else "Booking: Open."
        val dropAddress = if(dropoffAddress.isNullOrBlank()) "" else "Dropoff Address: $dropoffAddress"
        val pickAddress = if (pickupAddress.isNullOrBlank()) "" else "Pickup Address: $pickupAddress"
        textView.text =     """
        $status
        $bookingStatus
        $dropAddress
        $pickAddress
    """"
    }
}