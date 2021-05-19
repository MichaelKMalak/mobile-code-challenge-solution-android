package io.mobilityCompany.mobile_code_challenge.mainScreen.dagger

import android.content.res.Resources
import dagger.Component
import io.mobilityCompany.mobile_code_challenge.base.dagger.ApplicationScope
import io.mobilityCompany.mobile_code_challenge.mainScreen.interactor.MainScreenInteractor
import io.mobilityCompany.mobile_code_challenge.mainScreen.presenter.MainScreenPresenter
import io.mobilityCompany.mobile_code_challenge.mainScreen.view.MainScreenActivity
import io.mobilityCompany.mobile_code_challenge.network.NetworkModule

@ApplicationScope
@Component(modules = [MainScreenModule::class, NetworkModule::class])
interface MainScreenComponent {

  fun injectMainScreenActivity(mainScreenActivity: MainScreenActivity)

  val mainScreenPresenter: MainScreenPresenter

  val mainScreenInteractor: MainScreenInteractor

  val resources: Resources
}