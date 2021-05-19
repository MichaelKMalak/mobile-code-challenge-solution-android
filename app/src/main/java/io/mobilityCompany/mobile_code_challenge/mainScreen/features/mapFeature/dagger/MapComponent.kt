package io.mobilityCompany.mobile_code_challenge.mainScreen.features.mapFeature.dagger

import dagger.Component
import io.mobilityCompany.mobile_code_challenge.base.dagger.FeatureScope
import io.mobilityCompany.mobile_code_challenge.mainScreen.dagger.MainScreenComponent
import io.mobilityCompany.mobile_code_challenge.mainScreen.features.mapFeature.view.MapLayout

@FeatureScope
@Component(dependencies = [MainScreenComponent::class], modules = [MapModule::class])
interface MapComponent {

  fun injectMapView(mapLayout: MapLayout)
}