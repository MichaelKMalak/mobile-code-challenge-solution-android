package io.door2door.mobile_code_challenge.mainScreen.features.rideUpdates.dagger

import dagger.Component
import io.door2door.mobile_code_challenge.base.dagger.FeatureScope
import io.door2door.mobile_code_challenge.mainScreen.dagger.MainScreenComponent
import io.door2door.mobile_code_challenge.mainScreen.features.rideUpdates.view.RideUpdatesLayout

@FeatureScope
@Component(dependencies = [MainScreenComponent::class], modules = [RideUpdatesModule::class])
interface RideUpdatesComponent {

  fun injectRideUpdatesView(rideUpdatesLayout: RideUpdatesLayout)
}