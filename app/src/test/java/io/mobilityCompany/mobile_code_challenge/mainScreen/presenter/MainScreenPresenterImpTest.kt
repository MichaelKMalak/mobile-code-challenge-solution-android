package io.mobilityCompany.mobile_code_challenge.mainScreen.presenter

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.mobilityCompany.mobile_code_challenge.mainScreen.interactor.MainScreenInteractor
import org.junit.Before
import org.junit.Test

class MainScreenPresenterImpTest {

    private val mainScreenInteractor = mock<MainScreenInteractor>()

    private lateinit var mainScreenPresenter: MainScreenPresenter

    @Before
    fun setUp() {
        mainScreenPresenter = MainScreenPresenterImp(mainScreenInteractor = mainScreenInteractor)
    }

    @Test
    fun shouldConnectToWebSockets() {
        mainScreenPresenter.viewCreated()
        verify(mainScreenInteractor).connectToWebSocket()
    }
}