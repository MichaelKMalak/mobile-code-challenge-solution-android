package io.door2door.mobile_code_challenge.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import io.door2door.mobile_code_challenge.base.dagger.ApplicationScope
import io.door2door.mobile_code_challenge.data.events.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

private const val TIMEOUT_SECONDS = 30L

@Module
class NetworkModule {

  @ApplicationScope
  @Provides
  fun providesHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    return OkHttpClient.Builder()
        .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor)
        .build()
  }

  @ApplicationScope
  @Provides
  fun providesMoshi(): Moshi {
    return Moshi.Builder()
        .add(
            PolymorphicJsonAdapterFactory.of(Event::class.java, "event")
                .withSubtype(BookingOpened::class.java, EventType.bookingOpened.name)
                .withSubtype(BookingClosed::class.java, EventType.bookingClosed.name)
                .withSubtype(StatusUpdated::class.java, EventType.statusUpdated.name)
                .withSubtype(
                    VehicleLocationUpdated::class.java,
                    EventType.vehicleLocationUpdated.name
                )
                .withSubtype(
                    IntermediateStopLocationsChanged::class.java,
                    EventType.intermediateStopLocationsChanged.name
                )
        )
        .add(KotlinJsonAdapterFactory())
        .build()
  }

  @ApplicationScope
  @Provides
  fun providesBookingWebSocket(webSocketInterface: BookingsWebSocketImp): BookingsWebSocket =
      webSocketInterface
}