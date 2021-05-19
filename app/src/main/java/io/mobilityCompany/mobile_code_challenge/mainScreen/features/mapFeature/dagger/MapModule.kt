package io.mobilityCompany.mobile_code_challenge.mainScreen.features.mapFeature.dagger

import dagger.Module
import dagger.Provides
import io.mobilityCompany.mobile_code_challenge.base.dagger.FeatureScope
import io.mobilityCompany.mobile_code_challenge.mainScreen.features.mapFeature.presenter.MapPresenter
import io.mobilityCompany.mobile_code_challenge.mainScreen.features.mapFeature.presenter.MapPresenterImp
import io.mobilityCompany.mobile_code_challenge.mainScreen.features.mapFeature.view.MapView

@Module
class MapModule(private val mapView: MapView) {

    @FeatureScope
    @Provides
    fun providesMapView(): MapView = mapView

    @FeatureScope
    @Provides
    fun providesMapPresenter(mapPresenter: MapPresenterImp): MapPresenter = mapPresenter
}