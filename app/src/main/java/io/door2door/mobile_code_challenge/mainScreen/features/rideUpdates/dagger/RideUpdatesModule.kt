package io.door2door.mobile_code_challenge.mainScreen.features.rideUpdates.dagger

import dagger.Module
import dagger.Provides
import io.door2door.mobile_code_challenge.base.dagger.FeatureScope
import io.door2door.mobile_code_challenge.mainScreen.features.rideUpdates.presenter.RideUpdatesPresenter
import io.door2door.mobile_code_challenge.mainScreen.features.rideUpdates.presenter.RideUpdatesPresenterImp
import io.door2door.mobile_code_challenge.mainScreen.features.rideUpdates.view.RideUpdatesView

@Module
class RideUpdatesModule(private val rideUpdatesView: RideUpdatesView) {

  @FeatureScope
  @Provides
  fun providesRideUpdatesView() = rideUpdatesView

  @FeatureScope
  @Provides
  fun providesRideUpdatesPresenter(
      rideUpdatesPresenter: RideUpdatesPresenterImp): RideUpdatesPresenter =
      rideUpdatesPresenter
}