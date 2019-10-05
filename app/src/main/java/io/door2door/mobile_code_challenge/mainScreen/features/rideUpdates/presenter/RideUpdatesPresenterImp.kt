package io.door2door.mobile_code_challenge.mainScreen.features.rideUpdates.presenter

import android.util.Log
import io.door2door.mobile_code_challenge.mainScreen.features.rideUpdates.mapper.BookingStatusMapper
import io.door2door.mobile_code_challenge.mainScreen.features.rideUpdates.view.RideUpdatesView
import io.door2door.mobile_code_challenge.mainScreen.interactor.MainScreenInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RideUpdatesPresenterImp @Inject constructor(
    private val rideUpdatesView: RideUpdatesView,
    private val mainScreenInteractor: MainScreenInteractor,
    private val bookingStatusMapper: BookingStatusMapper
) : RideUpdatesPresenter {

    private val disposables = CompositeDisposable()
    private val tag = RideUpdatesPresenterImp::class.simpleName

    override fun viewAttached() {
        subscribeToBookingStatusUpdates()
    }

    override fun viewDetached() {
        disposables.dispose()
    }

    private fun subscribeToBookingStatusUpdates() {
        disposables.add(
            mainScreenInteractor.getBookingStatusUpdates(bookingStatusMapper)
                .subscribeOn(Schedulers.io()) //thread to run on
                .observeOn(AndroidSchedulers.mainThread()) //thread subscriber runs on
                .subscribe({
                    rideUpdatesView.showBookingStatus(
                        it.status,
                        it.isBookingClosed,
                        it.pickupAddress,
                        it.dropoffAddress
                    )
                    Log.d(tag, it.status)
                }, {
                    Log.d(tag, "Error on getting status updates")
                })
        )
    }
}

