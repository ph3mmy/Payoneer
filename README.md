# Payment Method
An Android Application interacting with [Payoneer Test API](https://raw.githubusercontent.com/optile/checkout-android/develop/shared-test/lists/listresult.json).
It displays links and information of various Payment Methods. This app displays the available payment methods and their logos

## Development Environment
* Android Studio 4.2.2
* Language: Java
* Build System: Gradle

## Features
* Clean Architecture + Model View Model Model Pattern + Repository Pattern.
* Jetpack Libraries and Architecture Component

## Architecture
Clean architecture was used in the project to organize the project into different layers to facilitate scalability, testability and easy readability.

The project is divided into 3 layers
* domain
* data
* app

### Domain
The business logic of the application was defined in this layer. It contains all the abstract definition and the inner most. It is completely made of java module
In this project the domain layers holds the definitions of use-cases/interactors, domain data models and repository interfaces.

```Usecases/Interactors```  they are the business logic executors that converts and passes user actions like fetch data from data source either remote or local and gives it back to the requester.
Usecases/Interactors are the mediators between the repository and data layer

```Repository interface``` interface that must be implemented by data layer

### Data
The data module consists of network models, mappers, API services, and repository implementations. The Repository pattern is implemented in this layer and it is used to abstract
away concrete implementation of data source

```mappers```  they are used to convert/maps one data type to the other e.g. network Entities to domain model

### Presentation (App)
This layer contains the implementation of the three inner layers. This layers contains  User Interface, mainly Android Stuff like Activities, Fragments, ViewModel,


## Testing
Testing is done in each layer. Thats one of the advantages of Clean Architecture

```domain``` in the domain layer test is done using ```JUnit4``` to the use cases. Fake data is used to test the Repository to interact with the use case to assert that the correct data is returned.

```data``` in this layer test to the repository implementation and mappers. The mappers test is used to certify that the mapper maps the correct data and returns the correct type.

```app``` in this layer, My viewModel was tested to assert that the usecase interact with the repository to fetch data from the repository in the data layer
Fake Data are used to test the ViewModel.

# Libraries
* [Android Jetpack](https://developer.android.com/jetpack)
   * [Data Binding](https://developer.android.com/topic/libraries/data-binding/) The Data Binding Library is a support library that allows you to bind UI components in your layouts to data sources in your app using a declarative format rather than programmatically.
   * [Live Data](https://developer.android.com/topic/libraries/architecture/livedata) LiveData is lifecycle-aware, meaning it respects the lifecycle of other app components updating app component observers that are in an active lifecycle state.
   * [Hilt](https://dagger.dev/hilt/) Hilt provides a standard way to incorporate Dagger dependency injection into an Android application.
* [Retrofit](https://square.github.io/retrofit/) Type-safe HTTP client for Android and Java and Kotlin by Square, Inc.
* [Lombok](https://github.com/projectlombok/lombok) Lombok is a java library for automatically generating getter and setter methods for java classes
* [OkHttp interceptor](https://github.com/square/okhttp/tree/master/okhttp-logging-interceptor) Logs HTTP requests and responses
* [Material Design](https://material.io/develop/android/) Build beautiful, usable products using Material Components for Android
* [JUnit4](https://junit.org/junit4/) Unit Testing