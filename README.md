# Mobile Code Challenge Android Solution

This is a solution to the mobile code challenge by 'allygator shuttle', door2door's mobility service that operates in Berlin, and provides a mobile app that allows users to 
book pooled rides and track their progress.

The application is basically a simulation (visualization) on google map view for data sent from [door2door's websocket](https://d2d-frontend-code-challenge.herokuapp.com/docs)).

door2door's team implemented the basic architecture for this code and based on this framework I implemented a solution that satisfies 
the [requirements](https://github.com/door2door-io/d2d-code-challenges/tree/master/mobile):
 
1. shows the user the live location of their assigned vehicle on a map
2. shows the ride's current status (waiting, in the vehicle, dropped off) in a UI component
3. shows the ride's pickup and dropoff addresses in a UI component

## About the code
1. In the presentation layer, MVP model is used. 
2. Dagger 2.0 is used for Dependency Injection through out the code. 
3. The archeticture model is very clean. 
	1. 'Network' is in a separate module and communicates with the 'Interactor'. It also instentiates data classes like 'Event'
	2. The 'Interactor' communicated with 'presenters' in each of the main and the two features
	3. Each one of the two features is in MVP model so the view and the model communicate through the presenters
	4. Each feature's 'mapper' inherits 'data' interface and overides its 'mapDataModelToViewModel' in different classes
4. HTTPOK is used to handle communication with the websocket.
5. moshi is used to easily map the JSON objects with data.
6. RxKotlin and RxJava were used. Reactive programming is implemented for a more efficient use of resources setting the 'Event' as the observables so that the observers (subscribers) only work when notified. 

## What is new? (In the main branch)

1. In '..features.mapFeature'
	1. In '.presenter': 
		Added 'subscribeToVehicleLocationUpdates()' function implementation. It's called when the 'mapLoaded()'
	2. In '.view': 
		1. Added 'updateVehicleLocation(LatLng)' that is the subscriber of 'subscribeToVehicleLocationUpdates()'
		2. It updates 'animateMarker(LatLng)' and 'moveCamera(LatLng)' which shows the vehicle and move the map with it.
		3. Function 'loadVehicleMarker()' is called when the 'presenter' calls 'obtainGoogleMap()'.

2. In '..features.rideUpdates'
	1. In '.presenter': 
		Added a call to 'updateBookingStatus' in view
	2. In '.view': 
		1. Added 'updateBookingStatus' that takes many strings from the presenter (source: the network)
		2. This function only 'updatesAddresses' and 'updatesStatus'

3. In '..res/layout/feature_ride_updates'
	1.Added two text views: 'addressesTextView' with alignParentBottom and 'statusTextView' at the top of the screen.
	2. Made use of the supported textStyles and added a backround tint for readability.

## What is missing?
I am creating a branch for this repository in order to attempt to solve some of these missing features.
1. Unit testing for both  'RideUpdatesPresenterImp' and 'MapPresenterImp'.
2. A refresh button to refresh the connection with the web socket and handeling websocket's failure
3. Visualising intermediate stops that the vehicle will make between the pickup and dropoff locations. 
4. Displaying the navigation bearing angle of the vehicle to show which direction the vehicle is currently driving in.
5. Adding a release Google Map API_key for releasing the application.
6. Checking the Wifi connection and requesting it.


## How to use?
1. Import the project using Android studio. 
2. Go to `google_maps_api.xml` 
3. Google's API_key is set open to be used. However, if it was expired, kindly follow the instructions to create a Google Maps API key and paste it in the aforementioned xml file.
