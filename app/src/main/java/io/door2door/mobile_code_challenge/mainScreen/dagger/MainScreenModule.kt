package io.door2door.mobile_code_challenge.mainScreen.dagger

import android.content.res.Resources
import dagger.Module
import dagger.Provides
import io.door2door.mobile_code_challenge.base.dagger.ApplicationScope
import io.door2door.mobile_code_challenge.mainScreen.interactor.MainScreenInteractor
import io.door2door.mobile_code_challenge.mainScreen.interactor.MainScreenInteractorImp
import io.door2door.mobile_code_challenge.mainScreen.presenter.MainScreenPresenter
import io.door2door.mobile_code_challenge.mainScreen.presenter.MainScreenPresenterImp
import io.door2door.mobile_code_challenge.mainScreen.view.MainScreenActivity

@Module
class MainScreenModule(private val mainScreenView: MainScreenActivity) {

  @ApplicationScope
  @Provides
  fun providesMainScreenPresenter(
      mainScreenPresenter: MainScreenPresenterImp): MainScreenPresenter =
      mainScreenPresenter

  @ApplicationScope
  @Provides
  fun providesMainScreenInteractor(
      mainScreenInteractor: MainScreenInteractorImp): MainScreenInteractor =
      mainScreenInteractor

  @ApplicationScope
  @Provides
  fun providesResources(): Resources = mainScreenView.resources
}