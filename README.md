# Mobile Code Challenge Android Solution

This is a solution to the mobile code challenge by 'allygator shuttle', door2door's mobility service that operates in Berlin, and provides a mobile app that allows users to book pooled rides and track their progress.

The application is basically a simulation (visualization) on google map view for data sent from [door2door's WebSocket](https://d2d-frontend-code-challenge.herokuapp.com/docs).

door2door's team implemented the basic architecture for this code and based on this framework I implemented a solution that satisfies 
the [requirements](https://github.com/door2door-io/d2d-code-challenges/tree/master/mobile):
 
1. shows the user the live location of their assigned vehicle on a map
2. shows the ride's current status (waiting, in the vehicle, dropped off) in a UI component
3. shows the ride's pickup and drop-off addresses in a UI component

## About the code
1. In the presentation layer, MVP model is used. 
2. Dagger 2.0 is used for Dependency Injection through out the code. 
3. The architecture model is very clean. 
	1. `Network` is in a separate module and communicates with the `Interactor`. It also instantiates data classes like `Event`
	2. The `Interactor` communicated with `presenters` in each of the main and the two features
	3. Each one of the two features is in MVP model so the view and the model communicate through the presenters
	4. Each feature's `mapper` inherits `data` interface and overrides its `mapDataModelToViewModel` in different classes
4.OkHttp is used to handle communication with the WebSocket.
5. moshi is used to easily map the JSON objects with data.
6. RxKotlin and RxJava were used. Reactive programming is implemented for a more efficient use of resources setting the `Event` as the observables so that the observers (subscribers) only work when notified. 

## What is new? 
### Overview
1. Once the application opens it connects to the WebSocket and grabs the data which will:
..* Initialize the vehicle location.
..* Shows the pickup, drop-off, and intermediate stops markers.
..* Shows the status on the screen.
..* Shows the pickup and drop-off addresses on the screen.

2. With every "vehicleLocationUpdated" event:
The vehicle marker will be animated

3. With every "intermediateStopLocationsChanged" event:
..* The stops markers will be drawn (on top of each other for a better memory performance since no need to "restart" when we finish)
..* The screen shows the next stop address (assuming, the event will always be sent when a car reaches a stop)

4. With every "statusUpdated" and "bookingClosed" event:
The status and the booking status will be updated on the screen.
The program checks if status of booking is closed to clear all markers on screen.

## Issues
1. When the internet connection is out for a bit while the application is running, it does not notify the user.
2. When the user zooms in with elevation, the vehicle marker rotation is not right.

## Notes
1. I think the navigation bearing is already implemented by the rotation of the vehicle in `animateMarker`.
2. There is no need for a release Google API key.
3. Making the markers clickable to display addresses is redundant data that is not necessary since I display what is the next stop location on the screen already.
4. I designed the drawables on illustrator for a vectorized code. For some reason, google map needed the markers to be an image, so I had to convert the vector images (SVG) to PNG.

## Assumptions
1. The websocket will always send an event when the vehicle reaches an intermediate stop to update the "Heading to" text view. 
2. `Event.data` from the webSocket may be null but `Event.data[0].Address` will never be null but could be empty.

## What is missing?
1. Unit testing.
2. Handling webSocket's failure for a better UX.

## How to use?
1. Import the project using Android studio. 
2. Go to `google_maps_api.xml` 
3. Google's API_key is set open to be used. However, if it was expired, kindly follow the instructions to create a Google Maps API key and paste it in the aforementioned xml file.


## Screenshot
![](https://raw.githubusercontent.com/MichaelKMalak/mobile-code-challenge-solution-android/extra-2/img/Screenshot_4.png)
