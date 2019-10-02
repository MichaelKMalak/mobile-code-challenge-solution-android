# Mobile Code Challenge Android Solution

This is a partial solution for the mobile code challenge, on the basis of which you should be able to solve the requirements of the code challenge.


We have built the basic architecture, which is close to the one used in our actual app.
Based on this framework we expect you to implement a solution that satisfies 
the requirements listed in the [mobile code challenge](https://github.com/door2door-io/d2d-code-challenges/tree/master/mobile). 
Feel free to add more things, but it is not required in order to have your code challenge accepted.

## Project setup

Import the project using Android studio. Go to `google_maps_api.xml` and follow the instructions to 
create a Google Maps API key and paste it there.

## Some tips on how to work with the existing code

Use the methods from `MainScreenInteractor` to get Observable streams of the events coming 
from the WebSocket endpoint (see [documentation](https://d2d-frontend-code-challenge.herokuapp.com/docs)) 
and display them in a way you see fit. There is an example on how to subscribe to an Observable stream 
in the `RideUpdatesPresenter` implementation. If you have not worked with RxJava before, don't be intimidated.
We don't expect you to understand everything and become an expert, but rather want to see how you can work with 
it based on a given example.

In the `MapLayout` make use of the existing private methods to handle marker animation.
We have provided a vehicle icon which you can use, or feel free to add your own image or use 
the basic marker without an icon.

Implement your own design for the `feature_ride_updates` layout. You can make use of the 
colours and styles we have provided or add your own. Make sure to display all the relevant 
information.

## Resources

* Kotlin documentation and tutorials - https://kotlinlang.org
* Rx documentation - http://reactivex.io/
* RxJava - https://github.com/ReactiveX/RxJava

================================================

# Mobile code challenge

'allygator shuttle', door2door's mobility service that operates in Berlin, provides a mobile app that allows users to 
book pooled rides and track their progress. In order to enable users to be able to track the progress of their ride after they book it, 
door2door needs you to provide a solution which:

1. shows the user the live location of their assigned vehicle on a map
2. shows the ride's current status (waiting, in the vehicle, dropped off) in a UI component
3. shows the ride's pickup and dropoff addresses in a UI component

door2door has provided a WebSockets endpoint that will provide all of this information. It will send different events simulating the various states of a ridepooling ride. Your solution should visualise the information that is sent from this endpoint.

- [Documentation of the WebSockets endpoint](https://d2d-frontend-code-challenge.herokuapp.com/docs)
  - Note that this service goes to sleep when not in use, so it may take a few seconds to connect the first time you access it

## _Optional_ extras

The following points are optional features that you may choose to implement, but they are not required in order to have your solution be accepted.

- Visualise on the map the intermediate stops that the vehicle will make between the pickup and dropoff locations. Note that these stops may change over the duration of the journey.
- Visualise the [navigation bearing](https://en.wikipedia.org/wiki/Bearing_(navigation)) of the vehicle to show which direction the vehicle is currently driving in.

## Technical assumptions

- *If you are applying for an iOS Software Developer position*, then the solution must be implemented in **Swift**.
- *If you are applying for an Android Software Developer position*, then the solution should preferably be implemented in **Kotlin**, but we would accept Java solutions too.
- You are free to make use of any framework or library you please (if any), but you should justify your choice.

## Delivery of your solution

Please deliver your solution to us as a publicly accessible git repository, or in a ZIP file. The repository should contain full instructions for us to build and run the project.

## Reviewing

The following description will give you an understanding of how we review the code challenge. What matters to us is to learn how you write code and what you consider as clean code. For us it is more important to have an understandable project than a complex algorithm.

The criteria that we are looking for are the following:

- Documentation: Is the project and the code properly documented?
- Correctness: Is the task solved? Does the app handle properly all the events sent from the WebSockets enpoint? If there is anything missing, is the reason why it is missing documented?
- Technology: Which libraries or approaches are used? Do they make sense for the task? Justify why you've decided to use those technologies to solve the code challenge.
- Code quality: Is the code understandable and maintainable? What programming paradigm is being used? Is it implemented correctly? Is the project linted?
- Tests: How is the project tested? Does the project contain system and unit tests? Is the entire project tested or just parts of it?