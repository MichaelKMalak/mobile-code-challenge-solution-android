package io.mobilityCompany.mobile_code_challenge.mainScreen.features.rideUpdates.dagger

import dagger.Module
import dagger.Provides
import io.mobilityCompany.mobile_code_challenge.base.dagger.FeatureScope
import io.mobilityCompany.mobile_code_challenge.mainScreen.features.rideUpdates.presenter.RideUpdatesPresenter
import io.mobilityCompany.mobile_code_challenge.mainScreen.features.rideUpdates.presenter.RideUpdatesPresenterImp
import io.mobilityCompany.mobile_code_challenge.mainScreen.features.rideUpdates.view.RideUpdatesView

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