package io.door2door.mobile_code_challenge.mainScreen.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.door2door.mobile_code_challenge.mainScreen.dagger.DaggerMainScreenComponent
import io.door2door.mobile_code_challenge.mainScreen.dagger.MainScreenComponent
import io.door2door.mobile_code_challenge.mainScreen.dagger.MainScreenModule
import io.door2door.mobile_code_challenge.mainScreen.presenter.MainScreenPresenter
import kotlinx.android.synthetic.main.feature_map.*
import javax.inject.Inject


class MainScreenActivity : AppCompatActivity() {

  @Inject
  lateinit var mainScreenPresenter: MainScreenPresenter

  val mainScreenComponent: MainScreenComponent by lazy {
    DaggerMainScreenComponent.builder()
        .mainScreenModule(MainScreenModule(this))
        .build()
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    injectDependencies()
    hideActionBar()
    setContentView(io.door2door.mobile_code_challenge.R.layout.activity_main_screen)
    mapView.onCreate(savedInstanceState)
    mainScreenPresenter.viewCreated()
  }

  private fun hideActionBar() {
    try {
      this.supportActionBar!!.hide()
    } catch (e: NullPointerException) {
      Log.d(MainScreenActivity::class.simpleName, "Error in hiding action bar")
    }
  }

  private fun injectDependencies() {
    mainScreenComponent.injectMainScreenActivity(this)
  }

  override fun onStart() {
    super.onStart()
    mapView.onStart()
  }

  override fun onResume() {
    super.onResume()
    mapView.onResume()
  }

  override fun onPause() {
    super.onPause()
    mapView.onPause()
  }

  override fun onStop() {
    super.onStop()
    mapView.onStop()
  }

  override fun onDestroy() {
    super.onDestroy()
    mapView.onDestroy()
  }

  override fun onSaveInstanceState(outState: Bundle) {
    super.onSaveInstanceState(outState)
    mapView.onSaveInstanceState(outState)
  }

  override fun onLowMemory() {
    super.onLowMemory()
    mapView.onLowMemory()
  }
}
