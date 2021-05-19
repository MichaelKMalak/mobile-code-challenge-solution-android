package io.mobilityCompany.mobile_code_challenge.mainScreen.presenter

import io.mobilityCompany.mobile_code_challenge.mainScreen.interactor.MainScreenInteractor
import javax.inject.Inject

class MainScreenPresenterImp @Inject constructor(
    private val mainScreenInteractor: MainScreenInteractor) : MainScreenPresenter {

  override fun viewCreated() {
    mainScreenInteractor.connectToWebSocket()
  }
}