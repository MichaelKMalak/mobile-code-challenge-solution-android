package io.door2door.mobile_code_challenge.mainScreen.features.rideUpdates.view

import android.content.Context
import android.content.res.Resources
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import androidx.core.view.isVisible
import io.door2door.mobile_code_challenge.R
import io.door2door.mobile_code_challenge.mainScreen.features.rideUpdates.dagger.DaggerRideUpdatesComponent
import io.door2door.mobile_code_challenge.mainScreen.features.rideUpdates.dagger.RideUpdatesModule
import io.door2door.mobile_code_challenge.mainScreen.features.rideUpdates.presenter.RideUpdatesPresenter
import io.door2door.mobile_code_challenge.mainScreen.view.MainScreenActivity
import kotlinx.android.synthetic.main.feature_ride_updates.view.*
import java.lang.StringBuilder
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
    override fun loadBookingStatus(status: String, isBookingClosed: Boolean, pickupAddress: String?, dropoffAddress: String?) {
       updateStatus(status, isBookingClosed)
       updateAddresses(pickupAddress, dropoffAddress, isBookingClosed)
    }

    private fun updateAddresses(pickupAddress: String?, dropoffAddress: String?, bookingClosed: Boolean) {
        if(dropoffAddress.isNullOrBlank() || pickupAddress.isNullOrBlank()){
            return
        }
        addressesTextView.isVisible = !bookingClosed
        addressesTextView.text = StringBuilder()
            .append(R.string.address_from)
            .append(pickupAddress)
            .append(R.string.address_to)
            .append(dropoffAddress)
    }

    private fun updateStatus(status: String, bookingClosed: Boolean) {
        statusTextView.isVisible = true
        val bookingStatus = if (bookingClosed) " [Booking Closed]" else " [Booking Open]"
        statusTextView.text = StringBuilder()
            .append(status)
            .append(bookingStatus)
     }
}