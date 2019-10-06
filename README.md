# Mobile Code Challenge Android Solution

This is a solution to the mobile code challenge by 'allygator shuttle', door2door's mobility service that operates in Berlin, and provides a mobile app that allows users to book pooled rides and track their progress.

The application is basically a simulation (visualization) on google map view for data sent from [door2door's websocket](https://d2d-frontend-code-challenge.herokuapp.com/docs).

door2door's team implemented the basic architecture for this code and based on this framework I implemented a solution that satisfies 
the [requirements](https://github.com/door2door-io/d2d-code-challenges/tree/master/mobile):
 
1. shows the user the live location of their assigned vehicle on a map
2. shows the ride's current status (waiting, in the vehicle, dropped off) in a UI component
3. shows the ride's pickup and dropoff addresses in a UI component

## About the code
1. In the presentation layer, MVP model is used. 
2. Dagger 2.0 is used for Dependency Injection through out the code. 
3. The archeticture model is very clean. 
	1. `Network` is in a separate module and communicates with the `Interactor`. It also instantiates data classes like `Event`
	2. The `Interactor` communicated with `presenters` in each of the main and the two features
	3. Each one of the two features is in MVP model so the view and the model communicate through the presenters
	4. Each feature's `mapper` inherits `data` interface and overides its `mapDataModelToViewModel` in different classes
4. HTTPOK is used to handle communication with the websocket.
5. moshi is used to easily map the JSON objects with data.
6. RxKotlin and RxJava were used. Reactive programming is implemented for a more efficient use of resources setting the `Event` as the observables so that the observers (subscribers) only work when notified. 

## What is new? 

1. Added a stopLocationUpdateSubject 
	1. In the Network package in BookingWebSocketImp, the subject listens to `BookingOpened` and `IntermediateStopLocationsChanged` events when `onMessage()` is called.
	2. In MainScreenInteractor added `getStopLocationsUpdates()`

2. In `..features.mapFeature`
	1. In `.presenter`: 
		1.Added `subscribeToVehicleLocationUpdates()` function implementation. It's called when the `mapLoaded()` and it `updateVehicleLocation` in MapView 
		2.Added `subscribeToStopLocationsUpdates()` function implementation. It's called when the `mapLoaded()` and it `showStartEndMarkers` as well as `updateStopsMarkers` in MapView
	2. In `.view`: 
		1. Added `updateVehicleMarker(LatLng)` 
			1. Its first call will `initializeVehicleMarker(LatLng)` which will `showVehicleMarker(LatLng)` and `moveCamera(LatLng)` 
			2. It normally calls `animateMarker(LatLng)`
		2. Added `showStartEndMarkers(LatLng,LatLng)` and `updateStopsMarkers(intermediateStopLatLng: List<LatLng>)`
		3. Function `updateVehicleLocation()` is called when the `presenter` calls `obtainGoogleMap()`.

3. In `..features.rideUpdates`
	1. In `.presenter`: 
		Added a call to `updateBookingStatus` in view
	2. In `.view`: 
		1. Added `updateAddresses` and `updateStatus` that takes many strings from the presenter (source: the network and the websocket) and update textViews in rideUpdatesLayout.

4. In `..res/layout/feature_ride_updates`
	1. Added two text views: `addressesTextView` with alignParentBottom and `statusTextView` at the top of the screen.
	2. Made use of the supported textStyles and added a backround tint for readability.

5. Designed simple drawables on illustrator and used them to draw the markers on the map.

## What is missing?
1. Unit testing for both  `RideUpdatesPresenterImp` and `MapPresenterImp`.
2. A refresh button to refresh the connection with the web socket and handeling websocket's failure.
3. Adding a release Google Map API_key for releasing the application.
4. Checking the Wifi connection and requesting it.

## Notes
1. I think the navigation bearing is already implemented by the rotation of the vehicle in `animateMarker`.

## How to use?
1. Import the project using Android studio. 
2. Go to `google_maps_api.xml` 
3. Google's API_key is set open to be used. However, if it was expired, kindly follow the instructions to create a Google Maps API key and paste it in the aforementioned xml file.

## Screenshot
![](https://raw.githubusercontent.com/MichaelKMalak/mobile-code-challenge-solution-android/extras/img/Screenshot_2.png)
